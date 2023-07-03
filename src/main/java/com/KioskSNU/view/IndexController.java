package com.KioskSNU.view;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class IndexController {
    Map<Integer, HttpSession> seatMap;
    Map<Integer, HttpSession> roomMap;
    RSA rsa;
    SHA sha;
    AccountService accountService;

    @Autowired
    public IndexController(@Qualifier("seatMap") ConcurrentHashMap<Integer, HttpSession> seatMap,
                           @Qualifier("roomMap") ConcurrentHashMap<Integer, HttpSession> roomMap,
                           RSA rsa, SHA sha,
                           AccountService accountService) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.rsa = rsa;
        this.sha = sha;
        this.accountService = accountService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("user1", accountService.getById(1));
        return mav;
    }
}
