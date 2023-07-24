package com.KioskSNU.view.outside;

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
import java.util.List;

@Controller
@RequestMapping("/outside/challenge")
public class OutsideChallengeController {
    private ChallengeService challengeService;
    private ParticipationChallengeService participationChallengeService;

    @Autowired
    public OutsideChallengeController(
            ChallengeService challengeService,
            ParticipationChallengeService participationChallengeService
    ){
        this.challengeService = challengeService;
        this.participationChallengeService = participationChallengeService;
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

    @PostMapping("/add")
    public ModelAndView addChallenge(@RequestParam("selectedChallengeId") Integer selectedChallengeId, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
        ParticipationChallengeDTO participationChallengeDTO = new ParticipationChallengeDTO();
        participationChallengeDTO.setAccount_id(accountDTO.getId());
        participationChallengeDTO.setChallenge_id(selectedChallengeId);

        participationChallengeService.insert(participationChallengeDTO);


        mav.setViewName("redirect:/outside/end");


        mav.addObject(participationChallengeService.getById(selectedChallengeId));
        return mav;
    }
    @GetMapping("/add")
    public String getAdd(){
        return "outside/end";
    }





}
