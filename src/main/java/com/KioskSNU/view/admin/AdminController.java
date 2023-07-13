package com.KioskSNU.view.admin;

import com.KioskSNU.snu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("") //admin 관리자 페이지로 이동시 로그인을 확인하고 로그아웃, 마이페이지로 표시할지 체크
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

//        if (userId == null) { 구현중이라 일단 주석처리
//            return "admin/admin_login"; // 로그인 페이지로 리다이렉트
//        }

        model.addAttribute("loggedIn", true);
        return "admin/admin_main";
    }

    @RequestMapping("/adminlogout")
    public String adminLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // 세션 무효화

        return "redirect:/admin/adminlogin"; // 메인페이지로 리다이렉트
    }

    @RequestMapping("/adminmypage")
    public String adminMypage() {
        return "admin/admin_mypage";
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
    @RequestMapping("/adminnotificationwrite")
    public String adminNotificationWrite() {
        return "admin/admin_notificationwrite";
    }

}
