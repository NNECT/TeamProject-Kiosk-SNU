package com.KioskSNU.view.admin;

import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/challenge")
public class AdminChallengeController {
    @Autowired
    private final ChallengeService challengeService;

    @Autowired
    public AdminChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/admin_challenge");

        try {
            List<ChallengeDTO> list = challengeService.getAll();
            mav.addObject("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    @GetMapping("/read")
    public ModelAndView read(Integer id) {
        ModelAndView mav = new ModelAndView();

        try {
            ChallengeDTO challenge = challengeService.getById(id);
            mav.addObject("id", id);
            mav.addObject("challenge", challenge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("admin/admin_challengeEdit");
        return mav;
    }

    @PostMapping("/edit")
    public ModelAndView edit(ChallengeDTO challengeDTO, @RequestParam(value = "StartTime") String StartTime,
                             @RequestParam(value = "EndTime") String EndTime) {

        System.out.println(EndTime);
        System.out.println(StartTime);
        ModelAndView mav = new ModelAndView();

        try {
            // 시간 형식을 "HH:mm"으로 지정하여 LocalTime 객체 생성
            LocalTime startTime = LocalTime.parse(StartTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime endTime = LocalTime.parse(EndTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

            challengeDTO.setActiveStartTime(startTime);
            challengeDTO.setActiveEndTime(endTime);
            challengeDTO.setVisible(true);
            challengeService.update(challengeDTO);
            mav.addObject("challenge", challengeService.getById(challengeDTO.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("admin/admin_challenge");
        return mav;
    }


}
