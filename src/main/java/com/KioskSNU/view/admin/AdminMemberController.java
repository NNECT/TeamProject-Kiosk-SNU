package com.KioskSNU.view.admin;

import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminMemberController {

    private final AccountService accountService;

    @Autowired
    public AdminMemberController(AccountService accountService) {
        this.accountService = accountService;
    }

    //전체 회원목록
    @GetMapping("/admin/adminmember")
    public ModelAndView getMemberList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("memberList", accountService.getAll());
        mav.setViewName("admin/admin_member");
        return mav;
    }


}
