package com.KioskSNU.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLogoutController {

    @GetMapping("/admin/adminlogout")
    public String adminLogout(HttpSession session){

        session.invalidate();
        return "admin/admin_login";
    }

}
