package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import com.KioskSNU.snu.dto.TimeTicketDTO;
import com.KioskSNU.snu.service.TimeTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/outside/ticket")
public class OutsideTicketController {
    private final TimeTicketService timeTicketService;

    @Autowired
    public OutsideTicketController(TimeTicketService timeTicketService) {
        this.timeTicketService = timeTicketService;
    }

    @RequestMapping("/seat")
    @OutsideLoginRequired
    public ModelAndView seatProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/timeTicket");

        List<TimeTicketDTO> timeTicketList = timeTicketService.getAllByActive(true);
        mav.addObject("timeTicketList", timeTicketList);

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
