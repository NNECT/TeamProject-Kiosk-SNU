package com.KioskSNU.view.admin;


import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.SeatService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.KioskSNU.snu.service.UsageSeatService;
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
public class AdminAjaxSeatActivationController {
    private final UsageSeatService usageSeatService;
    private final SeatService seatService;
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final UsageCommutationTicketService usageCommutationTicketService;
    private final AccountService accountService;


    @RequestMapping("/ajax/seatActivation")
    public ResponseEntity<Map<String, String>> sendProcess(@RequestBody Map<String, String> map, HttpSession session) {

        String purpose = map.get("codePurpose");
        int number = Integer.parseInt(map.get("number"));

        switch (purpose) {

            case "seatActivate": {
                SeatDTO seatDTO = seatService.getBySeatNumber(number);
                seatDTO.setUsable(true);
                if (seatService.update(seatDTO) > 0) {
                    return ResponseEntity.ok(Map.of("result", "success"));
                }
                break;
            }
            case "seatDeactivate": {
                SeatDTO seatDTO = seatService.getBySeatNumber(number);
                seatDTO.setUsable(false);
                if (seatService.update(seatDTO) > 0) {
                    return ResponseEntity.ok(Map.of("result", "success"));
                }
                break;
            }
            case "seatEndUsage": {

                UsageSeatDTO usageseatDTO = seatMap.get(number);
                SeatDTO seatDTO = seatService.getBySeatNumber(number);
                AccountDTO accountDTO = accountService.getById(usageseatDTO.getAccount_id());

                //사용중에서 뺴주고, 자리 사용불가로 변경
                seatMap.remove(number);
                seatDTO.setUsable(false);
                seatService.update(seatDTO);

                //자리사용 기록시키기
                usageseatDTO.setEndDateTime(LocalDateTime.now());
                if (usageseatDTO.getId() == 0) usageSeatService.insert(usageseatDTO);
                else usageSeatService.update(usageseatDTO);

                //정기권 처리
                List<UsageCommutationTicketDTO> commutationTicketDTOList = usageCommutationTicketService.getAllByAccount(accountDTO);
                if (
                        !commutationTicketDTOList.isEmpty()
                                && !commutationTicketDTOList.get(0).getEndDate().isBefore(LocalDate.now())
                ) {
                    return ResponseEntity.ok(Map.of("result", "success"));
                }


                // 사용 시간 차감
                int usedTime = (int) ChronoUnit.MINUTES.between(usageseatDTO.getStartDateTime(), usageseatDTO.getEndDateTime());
                accountDTO.setRemainTime(Math.max(accountDTO.getRemainTime() - usedTime, 0));
                accountService.update(accountDTO);
                return ResponseEntity.ok(Map.of("result", "success"));



            }
        }
        return null;
    }
}

