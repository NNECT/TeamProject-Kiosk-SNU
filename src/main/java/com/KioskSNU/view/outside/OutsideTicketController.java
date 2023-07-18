package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.dto.TimeTicketDTO;
import com.KioskSNU.snu.service.CommutationTicketService;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.TimeTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/outside/ticket")
public class OutsideTicketController {
    private final TimeTicketService timeTicketService;
    private final CommutationTicketService commutationTicketService;
    private final RoomService roomService;

    @Autowired
    public OutsideTicketController(
            TimeTicketService timeTicketService,
            CommutationTicketService commutationTicketService,
            RoomService roomService
    ) {
        this.timeTicketService = timeTicketService;
        this.commutationTicketService = commutationTicketService;
        this.roomService = roomService;
    }

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
    public ModelAndView timeTicketPostProcess(@RequestParam("radio-button") int ticket, HttpSession session){
        ModelAndView mav = new ModelAndView();



        // 이미 라커를 가지고 있을 경우 라커티켓으로 이동하는 분기 추가 필요
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
    public ModelAndView commutationTicketPostProcess(){
        ModelAndView mav = new ModelAndView();



        // 이미 라커를 가지고 있을 경우 라커티켓으로 이동하는 분기 추가 필요
        mav.setViewName("redirect:/outside/locker");
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
        if (roomService.getByRoomNumber(roomNumber) == null) {
            System.out.println(roomNumber + "번 방 정보가 존재하지 않음");
            mav.setViewName("redirect:/outside/logout");
            return mav;
        }

        mav.addObject("room", roomService.getByRoomNumber(roomNumber));

        mav.setViewName("outside/roomTicket");
        return mav;
    }

    @PostMapping("/room")
    @OutsideLoginRequired
    public ModelAndView roomPostProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/outside/locker");
        return mav;
    }
}
