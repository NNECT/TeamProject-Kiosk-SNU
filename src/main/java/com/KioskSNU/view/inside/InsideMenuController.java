package com.KioskSNU.view.inside;

import com.KioskSNU.interceptor.InsideLoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsideMenuController {
    @RequestMapping("inside/menu")
    @InsideLoginRequired
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/menu");
        return mav;
    }
}
