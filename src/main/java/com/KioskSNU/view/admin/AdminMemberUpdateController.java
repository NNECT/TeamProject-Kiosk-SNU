package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.KioskSNU.snu.service.UsageLockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminMemberUpdateController {

    private final AccountService accountService;
    private final UsageCommutationTicketService usageCommutationTicketService;
    private final UsageLockerService usageLockerService;
    private final ParticipationChallengeService participationChallengeService;


    @Autowired
    public AdminMemberUpdateController(AccountService accountService, UsageCommutationTicketService usageCommutationTicketService, UsageLockerService usageLockerService, ParticipationChallengeService participationChallengeService) {
        this.accountService = accountService;
        this.usageCommutationTicketService = usageCommutationTicketService;
        this.usageLockerService = usageLockerService;
        this.participationChallengeService = participationChallengeService;
    }

    @GetMapping("/admin/adminmemberedit")
    @AdminLoginRequired
    public ModelAndView GetProcess(AccountDTO accountDTO){

        ModelAndView mav = new ModelAndView();
        mav.addObject("member",accountService.getById(accountDTO.getId()));

        //정기권인지 시간권인지
        List<UsageCommutationTicketDTO> subscription = usageCommutationTicketService.getAllByAccount(accountDTO);
        if(!subscription.isEmpty()){
            UsageCommutationTicketDTO latest = subscription.get(0);
            if(latest.getEndDate().isEqual(LocalDate.now())||latest.getEndDate().isAfter(LocalDate.now())){
                mav.addObject("memberSubscription","정기권");
            }else{
                mav.addObject("memberSubscription","시간권");
            }
        }else{
            mav.addObject("memberSubscription","시간권");
        }

        //사물함 사용여부
        List<UsageLockerDTO> locker = usageLockerService.getAllByAccount(accountDTO);
        if(!locker.isEmpty()){
            UsageLockerDTO latest = locker.get(0);
            if(latest.getEndDate().isEqual(LocalDate.now())||latest.getEndDate().isAfter(LocalDate.now())){
                mav.addObject("memberLockerStatus",latest.getLocker_lockerNumber());
            }else{
                mav.addObject("memberLockerStatus","이용안함");
            }
        }else{
            mav.addObject("memberLockerStatus","이용안함");
        }

        //챌린지 참여여부
        List<ParticipationChallengeDTO> challenge = participationChallengeService.getAllByAccount(accountDTO);
        if(!challenge.isEmpty()){
            ParticipationChallengeDTO latest = challenge.get(0);
            if(latest.getEndDateTime().isEqual(LocalDateTime.now())||latest.getStartDateTime().isAfter(LocalDateTime.now())){
                mav.addObject("memberChallengeProgress",latest.getChallenge_title());
            }else{
                mav.addObject("memberChallengeProgress","참여안함");
            }
        }else{
            mav.addObject("memberChallengeProgress","참여안함");
        }

        mav.setViewName("admin/admin_memberEdit");

        return mav;
    }

    @PostMapping("/admin/adminmemberedit")
    @AdminLoginRequired
    public ModelAndView PostProcess(AccountDTO accountDTO){

        ModelAndView mav = new ModelAndView();

        accountService.update(accountDTO);
        mav.setViewName("redirect:/admin/adminmember");
        return mav;

    }

}
