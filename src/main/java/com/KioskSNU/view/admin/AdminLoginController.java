package com.KioskSNU.view.admin;

import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class AdminLoginController {

    private final AdminService adminService;

    @Autowired
    public AdminLoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/admin/adminLogin")
    public String adminLogin(AdminDTO adminDTO, HttpSession session) throws Exception{
        AdminDTO admin = adminService.getByUsername(adminDTO.getUsername());

        if (admin != null && Objects.equals(admin.getPassword(), adminDTO.getPassword())){

            session.setAttribute("admin",adminService.getByUsername(adminDTO.getUsername()));
            session.setAttribute("loggedIn",true); //admin 관리자 페이지로 이동시 로그인을 확인하고 로그아웃, 마이페이지로 표시할지 체크

            return "admin/admin_main";
        }
        else {
            return "admin/admin_login";
        }
    }

}
