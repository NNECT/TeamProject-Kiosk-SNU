package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.KioskSNU.snu.service.UsageLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class AdminMemberController {
    private final AccountService accountService;
    private final UsageCommutationTicketService usageCommutationTicketService;
    private final UsageLockerService usageLockerService;
    private final ParticipationChallengeService participationChallengeService;
    private final RSA rsa;
    private final SHA sha;


    @RequestMapping("/admin/adminmember")
    @AdminLoginRequired
    //전체 회원목록
    public ModelAndView getMemberList() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", accountService.getAll());
        mav.setViewName("admin/admin_member");
        return mav;
    }

    //회원정보 수정
    @GetMapping("/admin/adminmemberedit")
    @AdminLoginRequired
    public ModelAndView getProcess(AccountDTO accountDTO) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("member", accountService.getById(accountDTO.getId()));

        //정기권인지 시간권인지
        List<UsageCommutationTicketDTO> subscription = usageCommutationTicketService.getAllByAccount(accountDTO);
        if (!subscription.isEmpty()) {
            UsageCommutationTicketDTO latest = subscription.get(0);
            if (latest.getEndDate().isEqual(LocalDate.now()) || latest.getEndDate().isAfter(LocalDate.now())) {
                mav.addObject("memberSubscription", "정기권");
            } else {
                mav.addObject("memberSubscription", "시간권");
            }
        } else {
            mav.addObject("memberSubscription", "시간권");
        }

        //사용중인 사물함 번호
        List<UsageLockerDTO> locker = usageLockerService.getAllByAccount(accountDTO);
        if (!locker.isEmpty()) {
            UsageLockerDTO latest = locker.get(0);
            if (latest.getEndDate().isEqual(LocalDate.now()) || latest.getEndDate().isAfter(LocalDate.now())) {
                mav.addObject("memberLockerStatus", latest.getLocker_lockerNumber());
            } else {
                mav.addObject("memberLockerStatus", "없음");
            }
        } else {
            mav.addObject("memberLockerStatus", "없음");
        }

        //참여중인 챌린지 제목
        List<ParticipationChallengeDTO> challenge = participationChallengeService.getAllByAccount(accountDTO);
        if (!challenge.isEmpty()) {
            ParticipationChallengeDTO latest = challenge.get(0);
            if (latest.getEndDateTime().isEqual(LocalDateTime.now()) || latest.getStartDateTime().isAfter(LocalDateTime.now())) {
                mav.addObject("memberChallengeProgress", latest.getChallenge_title());
            } else {
                mav.addObject("memberChallengeProgress", "없음");
            }
        } else {
            mav.addObject("memberChallengeProgress", "없음");
        }

        mav.setViewName("admin/admin_memberEdit");

        return mav;
    }

    @PostMapping("/admin/adminmemberedit")
    @AdminLoginRequired
    public ModelAndView postProcess(AccountDTO accountDTO) {
        ModelAndView mav = new ModelAndView();
        accountService.update(accountDTO);
        mav.setViewName("redirect:/admin/adminmember");
        return mav;
    }

    //회원삭제
    @RequestMapping("/admin/adminmemberdelete")
    @AdminLoginRequired
    public ModelAndView process(AccountDTO accountDTO) {
        ModelAndView mav = new ModelAndView();
        accountService.delete(accountDTO);
        mav.setViewName("redirect:/admin/adminmember");
        return mav;
    }

    //비밀번호 초기화
    @RequestMapping("/admin/memberPasswordReset")
    @AdminLoginRequired
    public ModelAndView passwordReset(AccountDTO accountDTO) throws NoSuchAlgorithmException {
        ModelAndView mav = new ModelAndView();
        
        //넘어온 id로 유저 찾기
        AccountDTO getUser = accountService.getById(accountDTO.getId());

        //연락처 뒤 4자리, 맨뒤 3자리 입력된 경우 하이픈 제거로 4자리 맞추기
        String originPhoneNum = getUser.getPhoneNumber().replaceAll("-", "");
        String modifiedPhoneNum = originPhoneNum.substring(originPhoneNum.length() - 4);
        //4자리 sha 암호화
        getUser.setPassword(sha.encrypt(modifiedPhoneNum));
        accountService.update(getUser);

        mav.setViewName("admin/admin_member");

        return mav;
    }

}
