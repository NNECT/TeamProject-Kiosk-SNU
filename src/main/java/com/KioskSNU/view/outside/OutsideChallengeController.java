package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/outside/challenge")
public class OutsideChallengeController {
    private ChallengeService challengeService;

    @Autowired
    public OutsideChallengeController(ChallengeService challengeService){this.challengeService = challengeService;}

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




}
