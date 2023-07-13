package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/outside/ticket")
public class OutsideTicketController {

    @RequestMapping("/seat")
    @OutsideLoginRequired
    public ModelAndView seatProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/timeTicket");
        return mav;
    }

    @RequestMapping("/room")
    @OutsideLoginRequired
    public ModelAndView roomProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/timeTicket");
        return mav;
    }
}
