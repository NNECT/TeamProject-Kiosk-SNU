package com.KioskSNU.view.outside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OutsideTicketController {

    @RequestMapping("/outside/ticket")
    public ModelAndView process(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/timeTicket");
        return mav;
    }
}
