package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.KioskSNU.snu.service.UsageLockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminMemberGetController {
    private final AccountService accountService;
    private final UsageCommutationTicketService usageCommutationTicketService;
    private final UsageLockerService usageLockerService;
    private final ParticipationChallengeService participationChallengeService;

    @Autowired
    public AdminMemberGetController(AccountService accountService, UsageCommutationTicketService usageCommutationTicketService, UsageLockerService usageLockerService, ParticipationChallengeService participationChallengeService) {
        this.accountService = accountService;
        this.usageCommutationTicketService = usageCommutationTicketService;
        this.usageLockerService = usageLockerService;
        this.participationChallengeService = participationChallengeService;
    }

    //특정 회원정보
    @RequestMapping("/ajax/getMember")
    @AdminLoginRequired
    public ResponseEntity<Map<String,Object>> getMember(@RequestBody Map<String,String> map) {

        int id = Integer.parseInt(map.get("memberID"));
        AccountDTO accountDTO = accountService.getById(id);

        Map<String, Object> resultMap = new HashMap<>();
        if (accountDTO != null) {
            resultMap.put("result", "success");
            resultMap.put("memberId", accountDTO.getId());
            resultMap.put("memberName",accountDTO.getUsername());
            resultMap.put("memberPhone",accountDTO.getPhoneNumber());

            //정기권인지 시간권인지
            List<UsageCommutationTicketDTO> subscription = usageCommutationTicketService.getAllByAccount(accountDTO);
            if(!subscription.isEmpty()){
                UsageCommutationTicketDTO latest = subscription.get(0);
                if(latest.getEndDate().isEqual(LocalDate.now())||latest.getEndDate().isAfter(LocalDate.now())){
                    resultMap.put("memberSubscription","정기권");
                }else{
                    resultMap.put("memberSubscription","시간권");
                }
            }else{
                resultMap.put("memberSubscription","시간권");
            }

            //남은 시간
            resultMap.put("memberRemainTime",accountDTO.getRemainTime());

            //사물함 사용여부
            List<UsageLockerDTO> locker = usageLockerService.getAllByAccount(accountDTO);
            if(!locker.isEmpty()){
                UsageLockerDTO latest = locker.get(0);
                if(latest.getEndDate().isEqual(LocalDate.now())||latest.getEndDate().isAfter(LocalDate.now())){
                    //resultMap.put("memberLockerStatus","이용함");
                    resultMap.put("memberLockerStatus",latest.getLocker_lockerNumber());
                }else{
                    resultMap.put("memberLockerStatus","이용안함");
                }
            }else{
                resultMap.put("memberLockerStatus","이용안함");
            }

            //챌린지 참여여부
            List<ParticipationChallengeDTO> challenge = participationChallengeService.getAllByAccount(accountDTO);
            if(!challenge.isEmpty()){
                ParticipationChallengeDTO latest = challenge.get(0);
                if(latest.getEndDateTime().isEqual(LocalDateTime.now())||latest.getStartDateTime().isAfter(LocalDateTime.now())){
                    //resultMap.put("memberChallengeProgress","참여중");
                    resultMap.put("memberChallengeProgress",latest.getChallenge_title());
                }else{
                    resultMap.put("memberChallengeProgress","참여안함");
                }
            }else{
                resultMap.put("memberChallengeProgress","참여안함");
            }

            //포인트
            resultMap.put("memberPoint",accountDTO.getPoint());

        }
        return ResponseEntity.ok(resultMap);
    }

}


