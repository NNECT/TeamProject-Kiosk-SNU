package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OutsideFindUsernameController {
    private final AccountService accountService;

    @GetMapping("/outside/findUsername")
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/findUsername");
        return mav;
    }

    @PostMapping("/outside/findUsername")
    public ModelAndView postProcess(AccountDTO accountDTO){
        ModelAndView mav = new ModelAndView();

        //넘어온 phoneNumber로 회원조회
        List<AccountDTO> getUserList = accountService.getAllByPhoneNumber(accountDTO.getPhoneNumber());
        mav.addObject("username",getUserList.get(0).getUsername());
        mav.setViewName("/outside/finishFoundId");

        return mav;
    }
}
