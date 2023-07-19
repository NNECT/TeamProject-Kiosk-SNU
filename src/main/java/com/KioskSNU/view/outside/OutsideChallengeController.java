package com.KioskSNU.view.outside;

import com.KioskSNU.snu.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OutsideChallengeController {
    private ChallengeService challengeService;

    @Autowired
    public OutsideChallengeController(ChallengeService challengeService){this.challengeService = challengeService;}

    @RequestMapping("/outside/challenge")
    public ModelAndView process(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/challenge");
        return mav;
    }




}
