package com.KioskSNU.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminLockerController {

    @GetMapping("/admin/adminlocker")
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/admin_locker");
        return mav;
    }

}
