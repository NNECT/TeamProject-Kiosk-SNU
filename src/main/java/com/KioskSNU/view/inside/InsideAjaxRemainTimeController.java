package com.KioskSNU.view.inside;

import com.KioskSNU.api.AccountCommutationTicketCheck;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class InsideAjaxRemainTimeController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    private final AccountCommutationTicketCheck accountCommutationTicketCheck;
    private final AccountService accountService;

    @Autowired
    public InsideAjaxRemainTimeController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            AccountCommutationTicketCheck accountCommutationTicketCheck,
            AccountService accountService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.accountCommutationTicketCheck = accountCommutationTicketCheck;
        this.accountService = accountService;
    }

    @RequestMapping("/ajax/remainTime")
    public ResponseEntity<Map<String, String>> process(@RequestBody Map<String, String> map) {
        Map<String, String> result = new HashMap<>();

        // 데이터 확인
        int id;
        int insideNumber;
        try {
            id = Integer.parseInt(map.get("authorId"));
            insideNumber = Integer.parseInt(map.get("insideNumber"));
        } catch (NumberFormatException e) {
            result.put("result", "fail");
            return ResponseEntity.ok(result);
        }

        // 사용자 확인
        AccountDTO accountDTO = accountService.getById(id);
        if (accountDTO == null) {
            result.put("result", "fail");
            return ResponseEntity.ok(result);
        }

        switch (map.get("insideType")) {
            case "seat": {
                // 사용 데이터가 없으면 실패
                if (!seatMap.containsKey(insideNumber)) {
                    result.put("result", "fail");
                    return ResponseEntity.ok(result);
                }

                // 사용자가 다르면 실패
                UsageSeatDTO usageSeatDTO = seatMap.get(insideNumber);
                if (usageSeatDTO.getAccount_id() != accountDTO.getId()) {
                    result.put("result", "fail");
                    return ResponseEntity.ok(result);
                }

                // 정기권 확인
                UsageCommutationTicketDTO usageCommutationTicketDTO = accountCommutationTicketCheck.getCommutationTicket(accountDTO);
                if (usageCommutationTicketDTO != null) {
                    result.put("commutationTicket", "true");
                    result.put("remainTime", usageCommutationTicketDTO.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    break;
                }
                result.put("commutationTicket", "false");

                // 남은 시간 계산
                int usedTime = (int) ChronoUnit.MINUTES.between(usageSeatDTO.getStartDateTime(), LocalDateTime.now());
                int remainMinutes = accountDTO.getRemainTime() - usedTime;

                if (remainMinutes <= 0) {
                    result.put("remainTime", "00시간 00분");
                    break;
                }

                int remainHours = remainMinutes / 60;
                remainMinutes %= 60;

                result.put("remainTime", String.format("%02d시간 %02d분 ", remainHours, remainMinutes));
                break;
            }

            case "room": {
                // 사용 데이터가 없으면 실패
                if (!roomMap.containsKey(insideNumber)) {
                    result.put("result", "fail");
                    return ResponseEntity.ok(result);
                }

                // 사용자가 다르면 실패
                UsageRoomDTO usageRoomDTO = roomMap.get(insideNumber);
                if (usageRoomDTO.getAccount_id() != accountDTO.getId()) {
                    result.put("result", "fail");
                    return ResponseEntity.ok(result);
                }

                // 정기권 확인
                result.put("commutationTicket", "false");

                // 남은 시간 계산 (종료시간 - 현재)
                int remainMinutes = (int) LocalDateTime.now().until(usageRoomDTO.getEndDateTime(), ChronoUnit.MINUTES);

                if (remainMinutes <= 0) {
                    result.put("remainTime", "00시간 00분");
                    break;
                }

                int remainHours = remainMinutes / 60;
                remainMinutes %= 60;

                result.put("remainTime", String.format("%02d시간 %02d분", remainHours, remainMinutes));
                break;
            }

            default:
                result.put("result", "fail");
                return ResponseEntity.ok(result);
        }

        result.put("result", "success");
        return ResponseEntity.ok(result);
    }
}
