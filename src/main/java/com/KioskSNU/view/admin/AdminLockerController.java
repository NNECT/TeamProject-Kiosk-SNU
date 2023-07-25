package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminLockerController {

    @GetMapping("/admin/adminlocker")
    @AdminLoginRequired
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/admin_locker");
        return mav;
    }

}
