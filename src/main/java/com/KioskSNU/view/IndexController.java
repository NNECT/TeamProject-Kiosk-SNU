package com.KioskSNU.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "admin/admin_main";
    }
    
    @RequestMapping("/adminlogin")
    public String adminLogin() {
        return "admin/admin_login";
    }
    
    @RequestMapping("/adminlocker")
    public String adminLocker() {
        return "admin/admin_locker";
    }
    
    @RequestMapping("/adminmember")
    public String adminMember() {
        return "admin/admin_member";
    }
    
    @RequestMapping("/adminnotification")
    public String adminNotification() {
        return "admin/admin_notification";
    }
    
    @RequestMapping("/adminsales")
    public String adminSales() {
        return "admin/admin_sales";
    }
    
    @RequestMapping("/adminseat")
    public String adminSeat() {
        return "admin/admin_seat";
    }
    
    @RequestMapping("/adminchallenge")
    public String adminChallenge() {
        return "admin/admin_challenge";
    }
    @RequestMapping("/adminnotificationWrite")
    public String adminNotificationWrite() {
    	return "admin/admin_notificationWrite";
    }

}
