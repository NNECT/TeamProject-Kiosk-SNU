package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminMemberListController {

    private final AccountService accountService;

    @Autowired
    public AdminMemberListController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/admin/adminmember")
    @AdminLoginRequired
    public ModelAndView getMemberList(Integer id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("memberList", accountService.getAll());
        mav.setViewName("admin/admin_member");
        return mav;
    }

}
