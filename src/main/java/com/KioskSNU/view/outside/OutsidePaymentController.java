package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import com.KioskSNU.secure.RSA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import com.KioskSNU.snu.service.TicketMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OutsidePaymentController {
    private final RSA rsa;
    private final AccountService accountService;
    private final ParticipationChallengeService participationChallengeService;
    private final TicketMapService ticketMapService;

    @RequestMapping("/outside/payment")
    @OutsideLoginRequired
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        ticketMapService.setTicketModel(mav.getModelMap(), session);

        mav.addObject("publicKey", rsa.getPublicKey());
        mav.setViewName("outside/payment");
        return mav;
    }

    @RequestMapping("/outside/paymentSuccess")
    @OutsideLoginRequired
    public ModelAndView successProcess(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 이미 챌린지가 있을 경우
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
        ParticipationChallengeDTO participationChallengeDTO = participationChallengeService.getParticipationChallenge(accountDTO);
        if (participationChallengeDTO != null) {
            // 성공 체크
            int successCheck = participationChallengeService.challengeSuccessCheck(participationChallengeDTO);
            if (successCheck == 1) {
                // 성공 시
                participationChallengeDTO.setResult(true);
                participationChallengeDTO.setActive(false);
                participationChallengeService.update(participationChallengeDTO);
                // 포인트 적립
                accountDTO = accountService.getById(accountDTO.getId());
                accountDTO.setPoint(accountDTO.getPoint() + participationChallengeDTO.getRewardPoint());
                accountService.update(accountDTO);
            } else if (successCheck == -1) {
                // 실패 시
                participationChallengeDTO.setResult(false);
                participationChallengeDTO.setActive(false);
                participationChallengeService.update(participationChallengeDTO);
            } else {
                // 진행 중
                mav.setViewName("redirect:/outside/end");
                return mav;
            }
        }

        mav.setViewName("outside/paymentSuccess");
        return mav;
    }

    @RequestMapping("/outside/payment/cancel")
    @OutsideLoginRequired
    public ModelAndView cancelProcess(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 사용권 선택 초기화
        ticketMapService.clear();

        // 선택 타입에 따라 페이지 이동
        String selectType = (String) session.getAttribute("selectType");
        if (selectType == null) {
            mav.setViewName("redirect:/outside/logout");
            return mav;
        }
        switch (selectType) {
            case "seat":
                mav.setViewName("redirect:/outside/ticket/seat");
                break;
            case "room":
                mav.setViewName("redirect:/outside/ticket/room");
                break;
            default:
                mav.setViewName("redirect:/outside/logout");
                break;
        }

        return mav;
    }
}
