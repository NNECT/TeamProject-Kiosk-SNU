package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OutsideFindPasswordController {

    private final AccountService accountService;

    @Autowired
    public OutsideFindPasswordController(
            AccountService accountService
    ){
        this.accountService = accountService;
    }

    @GetMapping("/outside/findPassword")
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/findPassword");
        return mav;
    }

    @PostMapping("/outside/findPassword")
    public ModelAndView postProcess(AccountDTO accountDTO,HttpSession session){
        ModelAndView mav = new ModelAndView();

        AccountDTO getUser = accountService.getByUsername(accountDTO.getUsername());

        session.setAttribute("findPasswordUser",getUser);
        mav.setViewName("redirect:/outside/newPassword");

        return mav;
    }


}
