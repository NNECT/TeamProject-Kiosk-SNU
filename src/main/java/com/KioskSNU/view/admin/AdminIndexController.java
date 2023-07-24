package com.KioskSNU.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminIndexController {

    @RequestMapping("admin/index")
    public ModelAndView process(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/admin_main");
        return mav;
    }

}
