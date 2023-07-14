package com.KioskSNU.view.outside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class OutsideIndexController {
    @RequestMapping("/outside")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/index");
        if (session.getAttribute("selectType") != null) {
            mav.setViewName("redirect:/outside/logout");
        }
        return mav;
    }
}
