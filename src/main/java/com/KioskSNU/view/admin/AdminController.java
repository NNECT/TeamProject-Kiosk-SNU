package com.KioskSNU.view.admin;

import com.KioskSNU.snu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/adminmemberedit")
    public String adminMemberEdit() {
        return "admin/admin_memberEdit";
    }

    @RequestMapping("/adminnotification")
    public String adminNotification() {
        return "admin/admin_notification";
    }

    @RequestMapping("/adminsales")
    public String adminSales() {
        return "admin/admin_sales";
    }

    @RequestMapping("/adminpaysetting")
    public String adminPaySetting() {
        return "admin/admin_paySetting";
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
    @RequestMapping("/adminnotificationedit")
    public String adminNotificationEdit() {
        return "admin/admin_notificationedit";
    }

    @PostMapping("/update-prices")
    public String updatePrices(RedirectAttributes redirectAttributes){
//            매개변수 안에
//            (@RequestParam("hourlyPrice1") String hourlyPrice1,
//            @RequestParam("hourlyPrice2") String hourlyPrice2,
//                               // 나머지 요금 파라미터들도 추가로 받아옵니다.
//                               // 예: @RequestParam("monthlyPrice3") String monthlyPrice3,
//                               //     @RequestParam("lockerPrice30") String lockerPrice30,
//                               //     @RequestParam("roomPrice1") String roomPrice1,
//                               //     ...
//             {

        // 요금 업데이트 로직을 구현합니다.
        // 업데이트된 요금 값을 이용하여 데이터베이스 등에 저장하거나 처리합니다.

        // 업데이트 성공 메시지를 추가합니다.
        redirectAttributes.addFlashAttribute("successMessage", "요금이 업데이트되었습니다.");

        return "redirect:/admin/adminpaysetting"; // 관리자 페이지로 리다이렉트합니다.
    }
}
