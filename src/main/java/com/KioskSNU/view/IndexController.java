package com.KioskSNU.view;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
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
    UsageCommutationTicketService usageCommutationTicketService;

    @Autowired
    public IndexController(@Qualifier("seatMap") ConcurrentHashMap<Integer, HttpSession> seatMap,
                           @Qualifier("roomMap") ConcurrentHashMap<Integer, HttpSession> roomMap,
                           RSA rsa, SHA sha,
                           AccountService accountService,
                           UsageCommutationTicketService usageCommutationTicketService) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.rsa = rsa;
        this.sha = sha;
        this.accountService = accountService;
        this.usageCommutationTicketService = usageCommutationTicketService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("user1", accountService.getById(1));
        var usage = usageCommutationTicketService.getAll();
        System.out.println(usage + " " + usage.size());
        mav.addObject("usage", usage);
        return mav;
    }
}