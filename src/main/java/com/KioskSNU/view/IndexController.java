package com.KioskSNU.view;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class IndexController {
    Map<Integer, UsageSeatDTO> seatMap;
    Map<Integer, UsageRoomDTO> roomMap;
    RSA rsa;
    SHA sha;
    AccountService accountService;
    UsageCommutationTicketService usageCommutationTicketService;

    @Autowired
    public IndexController(@Qualifier("seatMap") ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
                           @Qualifier("roomMap") ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
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
        List<UsageCommutationTicketDTO> usage = usageCommutationTicketService.getAll();
        System.out.println(usage + " " + usage.size());
        mav.addObject("usage", usage);

        AccountDTO accountDTO = accountService.getByUsername("admin");
        System.out.println(accountDTO);

        String json = new Gson().toJson(usage);
        System.out.println(json);
        mav.addObject("usage2", json);

        return mav;
    }
}