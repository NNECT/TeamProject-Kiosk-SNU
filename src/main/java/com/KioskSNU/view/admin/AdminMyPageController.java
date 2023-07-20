package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminMyPageController {

    private final AdminService adminService;

    @Autowired
    public AdminMyPageController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/adminmypage")
    @AdminLoginRequired
    public ModelAndView adminMyPage(AdminDTO adminDTO){

        ModelAndView mav = new ModelAndView();
        mav.addObject("admin",adminService.getById(adminDTO.getId()));

        mav.setViewName("admin/admin_mypage");
        return mav;

    }

/*
    @PostMapping("/admin/adminmypage")
    public ModelAndView updateMyPage(AdminDTO adminDTO){

        ModelAndView mav = new ModelAndView();

        adminDTO.setId(adminDTO.getId());
        adminDTO.setUsername(adminDTO.getUsername());

        adminService.update(adminDTO);
        mav.setViewName("admin/admin_main");
        return mav;

    }
*/

}
