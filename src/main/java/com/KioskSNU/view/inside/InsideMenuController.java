package com.KioskSNU.view.inside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsideMenuController {
    @RequestMapping("inside/menu")
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/menu");
        return mav;
    }
}
