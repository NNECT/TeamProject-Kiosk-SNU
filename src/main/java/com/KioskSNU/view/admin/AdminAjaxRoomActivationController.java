package com.KioskSNU.view.admin;


import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
public class AdminAjaxRoomActivationController {
    private final UsageRoomService usageRoomService;
    private final RoomService roomService;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;
    private final UsageCommutationTicketService usageCommutationTicketService;
    private final AccountService accountService;


    @RequestMapping("/ajax/roomActivation")
    @AdminLoginRequired
    public ResponseEntity<Map<String, String>> sendProcess(@RequestBody Map<String, String> map, HttpSession session) {

        String purpose = map.get("codePurpose");
        int number = Integer.parseInt(map.get("number"));

        switch (purpose) {

            case "roomActivate": {
                RoomDTO roomDTO = roomService.getByRoomNumber(number);
                roomDTO.setUsable(true);
                if (roomService.update(roomDTO) > 0) {
                    return ResponseEntity.ok(Map.of("result", "success"));
                }
                break;
            }
            case "roomDeactivate": {
                RoomDTO roomDTO = roomService.getByRoomNumber(number);
                roomDTO.setUsable(false);
                if (roomService.update(roomDTO) > 0) {
                    return ResponseEntity.ok(Map.of("result", "success"));
                }
                break;
            }
            case "roomEndUsage": {

                UsageRoomDTO usageroomDTO = roomMap.get(number);
                RoomDTO roomDTO = roomService.getByRoomNumber(number);
                AccountDTO accountDTO = accountService.getById(usageroomDTO.getAccount_id());

                //사용중에서 뺴주고, 자리 사용불가로 변경
                roomMap.remove(number);
                roomDTO.setUsable(false);
                roomService.update(roomDTO);

                //자리사용 기록시키기
                usageroomDTO.setEndDateTime(LocalDateTime.now());
                if (usageroomDTO.getId() == 0) usageRoomService.insert(usageroomDTO);
                else usageRoomService.update(usageroomDTO);

                // 사용 시간 차감
                int usedTime = (int) ChronoUnit.MINUTES.between(usageroomDTO.getStartDateTime(), usageroomDTO.getEndDateTime());
                accountDTO.setRemainTime(Math.max(accountDTO.getRemainTime() - usedTime, 0));
                accountService.update(accountDTO);
                return ResponseEntity.ok(Map.of("result", "success"));
            }
        }
        return null;
    }
}

