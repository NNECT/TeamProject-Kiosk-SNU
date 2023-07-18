package com.KioskSNU.view.inside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsideMenuController {

    @GetMapping("inside/inside_menu")
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/inside_menu");
        return mav;
    }
}
