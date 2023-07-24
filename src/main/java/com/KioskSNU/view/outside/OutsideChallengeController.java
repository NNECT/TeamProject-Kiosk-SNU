package com.KioskSNU.view.outside;

import com.KioskSNU.api.ChallengeSuccessCheck;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.service.ChallengeService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
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
@RequestMapping("/outside/challenge")
public class OutsideChallengeController {
    private final ChallengeService challengeService;
    private final ParticipationChallengeService participationChallengeService;
    private final ChallengeSuccessCheck challengeSuccessCheck;

    @Autowired
    public OutsideChallengeController(
            ChallengeService challengeService,
            ParticipationChallengeService participationChallengeService,
            ChallengeSuccessCheck challengeSuccessCheck
    ){
        this.challengeService = challengeService;
        this.participationChallengeService = participationChallengeService;
        this.challengeSuccessCheck = challengeSuccessCheck;
    }

    @RequestMapping("/list")
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
    public String getAdd() {
        return "outside/end";
    }


    @PostMapping("/add")
    public ModelAndView addChallenge(@RequestParam("selectedChallengeId") Integer selectedChallengeId, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
            ChallengeDTO challengeDTO = challengeService.getById(selectedChallengeId);
            ParticipationChallengeDTO participationChallengeDTO = new ParticipationChallengeDTO();
            participationChallengeDTO.setAccount_id(accountDTO.getId());
            participationChallengeDTO.setChallenge_id(selectedChallengeId);
            participationChallengeDTO.setStartDateTime(LocalDateTime.now());
            participationChallengeDTO.setEndDateTime(calTime(challengeDTO));
            participationChallengeDTO.setGoalDay(challengeDTO.getGoalDay());
            participationChallengeDTO.setGoalHour(challengeDTO.getGoalHour());
            participationChallengeDTO.setRewardPoint(challengeDTO.getRewardPoint());
            participationChallengeDTO.setActive(isActive(participationChallengeDTO));
            participationChallengeDTO.setResult(checkSuccess(participationChallengeDTO));

            participationChallengeService.insert(participationChallengeDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("redirect:/outside/end");

        return mav;
    }
    public boolean checkSuccess(ParticipationChallengeDTO participationChallengeDTO) {
        int result = challengeSuccessCheck.challengeSuccessCheck(participationChallengeDTO);

        return result == 1;
    }
    public boolean isActive(ParticipationChallengeDTO participationChallengeDTO) {
        return challengeSuccessCheck.challengeSuccessCheck(participationChallengeDTO) == 0;
    }




    public LocalDateTime calTime(ChallengeDTO challengeDTO){
        LocalDateTime endTime = LocalDateTime.now().plusDays(challengeDTO.getPeriodDays()).plusHours(challengeDTO.getPeriodHours());

        return endTime;
    }






}
