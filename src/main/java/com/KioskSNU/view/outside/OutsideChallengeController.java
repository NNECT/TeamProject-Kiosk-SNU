package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.service.ChallengeService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/outside/challenge")
public class OutsideChallengeController {
    private final ChallengeService challengeService;
    private final ParticipationChallengeService participationChallengeService;

    @RequestMapping("/list")
    @OutsideLoginRequired
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();

        try {
            List<ChallengeDTO> list = challengeService.getAll();
            mav.addObject("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("outside/challenge");
        return mav;
    }

    @GetMapping("/add")
    @OutsideLoginRequired
    public String getAdd() {
        return "outside/end";
    }

    @PostMapping("/add")
    @OutsideLoginRequired
    public ModelAndView addChallenge(@RequestParam("selectedChallengeId") Integer selectedChallengeId, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            // 참여자, 챌린지 정보 가져오기
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
            ChallengeDTO challengeDTO = challengeService.getById(selectedChallengeId);

            // 참여자, 챌린지 정보를 담은 DTO 생성
            ParticipationChallengeDTO participationChallengeDTO = new ParticipationChallengeDTO();
            participationChallengeDTO.setAccountDTO(accountDTO);
            participationChallengeDTO.setChallengeDTO(challengeDTO);

            // 챌린지 시작, 종료 시간 설정
            participationChallengeDTO.setStartDateTime(LocalDateTime.now());
//            participationChallengeDTO.setEndDateTime(calTime(challengeDTO));
            participationChallengeService.setEndDateTime(participationChallengeDTO);

            // 챌린지 목표 정보 설정
            participationChallengeDTO.setGoalDay(challengeDTO.getGoalDay());
            participationChallengeDTO.setGoalHour(challengeDTO.getGoalHour());
            participationChallengeDTO.setRewardPoint(challengeDTO.getRewardPoint());

            // 챌린지 참여 초기화 설정
            participationChallengeDTO.setActive(true);
            participationChallengeDTO.setResult(false);

            // 챌린지 참여 정보 DB에 저장
            participationChallengeService.insert(participationChallengeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("redirect:/outside/end");

        return mav;
    }

//    /**
//     * 챌린지 성공 여부 확인
//     * @param participationChallengeDTO 챌린지 참여 정보 DTO
//     * @return 챌린지 성공 여부
//     */
//    public boolean checkSuccess(ParticipationChallengeDTO participationChallengeDTO) {
//        int result = participationChallengeService.challengeSuccessCheck(participationChallengeDTO);
//
//        return result == 1;
//    }

//    /**
//     * 챌린지가 진행중인지 확인
//     * @param participationChallengeDTO 챌린지 참여 정보 DTO
//     * @return 챌린지 진행 여부
//     */
//    public boolean isActive(ParticipationChallengeDTO participationChallengeDTO) {
//        return participationChallengeService.challengeSuccessCheck(participationChallengeDTO) == 0;
//    }

//    /**
//     * 챌린지 종료 시간 계산.
//     * 현재 시간 + 챌린지 기간.
//     * @param challengeDTO 챌린지 정보 DTO
//     * @return 챌린지 종료 시간
//     */
//    public LocalDateTime calTime(ChallengeDTO challengeDTO){
//        return LocalDateTime.now().plusDays(challengeDTO.getPeriodDays()).plusHours(challengeDTO.getPeriodHours());
//    }
}
