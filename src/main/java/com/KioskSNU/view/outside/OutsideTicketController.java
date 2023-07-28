package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.TimeTicketDTO;
import com.KioskSNU.snu.service.CommutationTicketService;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.TimeTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/outside/ticket")
public class OutsideTicketController {
    private final HashMap<String, Object> ticketMap;
    private final TimeTicketService timeTicketService;
    private final CommutationTicketService commutationTicketService;
    private final RoomService roomService;

    @GetMapping({"/seat", "/seat/timeTicket"})
    @OutsideLoginRequired
    public ModelAndView timeTicketGetProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/timeTicket");

        List<TimeTicketDTO> timeTicketList = timeTicketService.getAllByActive(true);
        mav.addObject("timeTicketList", timeTicketList);

        return mav;
    }

    @PostMapping("/seat/timeTicket")
    @OutsideLoginRequired
    public ModelAndView timeTicketPostProcess(@RequestParam("radio-button") int ticket){
        ModelAndView mav = new ModelAndView();

		TimeTicketDTO timeTicket = timeTicketService.getById(ticket);
        if (timeTicket == null) {
            mav.setViewName("redirect:/seat/timeTicket");
            return mav;
        }

        ticketMap.put("timeTicket", timeTicket);

        mav.setViewName("redirect:/outside/locker");
        return mav;
    }

    @GetMapping("/seat/commutationTicket")
    @OutsideLoginRequired
    public ModelAndView commutationTicketGetProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/commutationTicket");

        List<CommutationTicketDTO> commutationTicketList = commutationTicketService.getAllByActive(true);
        mav.addObject("commutationTicketList", commutationTicketList);

        return mav;
    }

    @PostMapping("/seat/commutationTicket")
    @OutsideLoginRequired
    public ModelAndView commutationTicketPostProcess(@RequestParam("radio-button") int ticket){
        ModelAndView mav = new ModelAndView();

        CommutationTicketDTO commutationTicket = commutationTicketService.getById(ticket);
        if (commutationTicket == null) {
            mav.setViewName("redirect:/seat/commutationTicket");
            return mav;
        }

        ticketMap.put("commutationTicket", commutationTicket);

        mav.setViewName("redirect:/inside/locker");
        return mav;
    }

    @GetMapping("/room")
    @OutsideLoginRequired
    public ModelAndView roomGetProcess(HttpSession session){
        ModelAndView mav = new ModelAndView();

        // 선택이 되지 않았거나, 선택이 룸이 아닐 경우
        if (session.getAttribute("selectType") == null || !session.getAttribute("selectType").equals("room")) {
            mav.setViewName("redirect:/outside/logout");
            return mav;
        }

        // 선택한 방이 존재하지 않을 경우
        int roomNumber = (int) session.getAttribute("selectNumber");
        RoomDTO room = roomService.getByRoomNumber(roomNumber);
        if (room == null) {
            System.out.println(roomNumber + "번 방 정보가 존재하지 않음");
            mav.setViewName("redirect:/outside/logout");
            return mav;
        }

        mav.addObject("room", room);

        mav.setViewName("outside/roomTicket");
        return mav;
    }

    @PostMapping("/room")
    @OutsideLoginRequired
    public ModelAndView roomPostProcess(@RequestParam("radio-button") int time){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/outside/payment");

        ticketMap.put("roomTicket", time);

        return mav;
    }
}
