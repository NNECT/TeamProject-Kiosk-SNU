package com.KioskSNU.view.inside;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.TimeTicketDTO;
import com.KioskSNU.snu.service.CommutationTicketService;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.TimeTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/inside/ticket")
public class InsideTicketController {
    private final TimeTicketService timeTicketService;
    private final CommutationTicketService commutationTicketService;
    private final RoomService roomService;

    @Autowired
    public InsideTicketController(
            TimeTicketService timeTicketService,
            CommutationTicketService commutationTicketService,
            RoomService roomService
    ) {
        this.timeTicketService = timeTicketService;
        this.commutationTicketService = commutationTicketService;
        this.roomService = roomService;
    }

    @RequestMapping("")
    public ModelAndView process(HttpSession session){
        ModelAndView mav = new ModelAndView();

        String type = (String) session.getAttribute("insideType");
        switch (type) {
            case "seat":
                mav.setViewName("redirect:/inside/ticket/seat");
                break;
            case "room":
                mav.setViewName("redirect:/inside/ticket/room");
                break;
            default:
                System.out.println("InsideTicketController.process: type is not seat or room");
                mav.setViewName("redirect:/inside");
                break;
        }

        return mav;
    }

    @GetMapping({"/seat", "/seat/timeTicket"})
    public ModelAndView timeTicketGetProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/timeTicket");

        List<TimeTicketDTO> timeTicketList = timeTicketService.getAllByActive(true);
        mav.addObject("timeTicketList", timeTicketList);

        return mav;
    }

    @PostMapping("/seat/timeTicket")
    public ModelAndView timeTicketPostProcess(HttpSession session){
        ModelAndView mav = new ModelAndView();
        // 프로세스 추가 필요
        mav.setViewName("redirect:/inside/menu");
        return mav;
    }

    @GetMapping("/seat/commutationTicket")
    public ModelAndView commutationTicketGetProcess(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/commutationTicket");

        List<CommutationTicketDTO> commutationTicketList = commutationTicketService.getAllByActive(true);
        mav.addObject("commutationTicketList", commutationTicketList);

        return mav;
    }

    @PostMapping("/seat/commutationTicket")
    public ModelAndView commutationTicketPostProcess(HttpSession session){
        ModelAndView mav = new ModelAndView();
        // 프로세스 추가 필요
        mav.setViewName("redirect:/inside/menu");
        return mav;
    }

    @GetMapping("/room")
    public ModelAndView roomGetProcess(HttpSession session){
        ModelAndView mav = new ModelAndView();

        // 등록이 되지 않은 경우
        if (session.getAttribute("insideType") == null) {
            mav.setViewName("redirect:/inside");
            return mav;
        }

        // 좌석이 등록된 경우
        if (session.getAttribute("insideType").equals("seat")) {
            mav.setViewName("redirect:/inside/ticket/seat");
            return mav;
        }

        // 정상적인 등록이 아닐 경우
        if (!session.getAttribute("insideType").equals("room")) {
            mav.setViewName("redirect:/inside");
        }

        int roomNumber = (int) session.getAttribute("insideNumber");
        RoomDTO room = roomService.getByRoomNumber(roomNumber);
        if (room == null) {
            System.out.println(roomNumber + "번 방 정보가 존재하지 않음");
            mav.setViewName("redirect:/inside");
            return mav;
        }

        mav.addObject("room", room);

        mav.setViewName("inside/roomTicket");
        return mav;
    }

    @PostMapping("/room")
    public ModelAndView roomPostProcess(HttpSession session){
        ModelAndView mav = new ModelAndView();
        // 프로세스 추가 필요
        mav.setViewName("redirect:/inside/menu");
        return mav;
    }
}
