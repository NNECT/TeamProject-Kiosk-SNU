package com.KioskSNU.view.inside;

import com.KioskSNU.interceptor.InsideLoginRequired;
import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inside/ticket")
public class InsideTicketController {
    private final TicketMapService ticketMapService;
    private final Set<Integer> lockerSet;
    private final TimeTicketService timeTicketService;
    private final CommutationTicketService commutationTicketService;
    private final RoomService roomService;
    private final UsageLockerService usageLockerService;
    private final LockerService lockerService;
    private final LockerTicketService lockerTicketService;

    @RequestMapping("")
    @InsideLoginRequired
    public ModelAndView process(HttpSession session) {
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
    @InsideLoginRequired
    public ModelAndView timeTicketGetProcess() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/timeTicket");

        List<TimeTicketDTO> timeTicketList = timeTicketService.getAllByActive(true);
        mav.addObject("timeTicketList", timeTicketList);

        return mav;
    }

    @PostMapping("/seat/timeTicket")
    @InsideLoginRequired
    public ModelAndView timeTicketPostProcess(@RequestParam("radio-button") int ticket) {
        ModelAndView mav = new ModelAndView();
        // 티켓 확인
        TimeTicketDTO timeTicket = timeTicketService.getById(ticket);
        if (timeTicket == null) {
            mav.setViewName("redirect:/inside/ticket/seat");
            return mav;
        }

        ticketMapService.putTimeTicket(timeTicket);

        mav.setViewName("redirect:/inside/payment");
        return mav;
    }

    @GetMapping("/seat/commutationTicket")
    @InsideLoginRequired
    public ModelAndView commutationTicketGetProcess() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/commutationTicket");

        List<CommutationTicketDTO> commutationTicketList = commutationTicketService.getAllByActive(true);
        mav.addObject("commutationTicketList", commutationTicketList);

        return mav;
    }

    @PostMapping("/seat/commutationTicket")
    @InsideLoginRequired
    public ModelAndView commutationTicketPostProcess(@RequestParam("radio-button") int ticket) {
        ModelAndView mav = new ModelAndView();
        // 티켓 확인
        CommutationTicketDTO commutationTicket = commutationTicketService.getById(ticket);
        if (commutationTicket == null) {
            mav.setViewName("redirect:/inside/ticket/seat/commutationTicket");
            return mav;
        }

        ticketMapService.putCommutationTicket(commutationTicket);

        mav.setViewName("redirect:/inside/payment");
        return mav;
    }

    @GetMapping("/room")
    @InsideLoginRequired
    public ModelAndView roomGetProcess(HttpSession session) {
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
    @InsideLoginRequired
    public ModelAndView roomPostProcess(@RequestParam("radio-button") int ticket) {
        ModelAndView mav = new ModelAndView();

        ticketMapService.putRoomTicket(ticket);

        mav.setViewName("redirect:/inside/payment");
        return mav;
    }

    @GetMapping("/locker")
    @InsideLoginRequired
    public ModelAndView lockerGetProcess(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 사용자 확인
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");

        // 사물함 보유 여부 확인
        boolean hasLocker = usageLockerService.hasLocker(accountDTO);
        mav.addObject("hasLocker", hasLocker);

        // 사물함 상태 확인
        Map<Integer, Integer> lockerStatusMap = usageLockerService.getLockerStatusMap(accountDTO);
        mav.addObject("lockerStatusMap", new Gson().toJson(lockerStatusMap));

        // 사물함 사용권 목록 확인
        mav.addObject("lockerTicketList", lockerTicketService.getAllByActive(true));

        mav.setViewName("inside/lockerTicket");
        return mav;
    }

    @PostMapping("/inside/locker")
    @InsideLoginRequired
    public ModelAndView lockerPostProcess(
            @RequestParam("radio-button") int ticket,
            @RequestParam(value = "locker-radio", required = false) String lockerNumber,
            HttpSession session
    ) {
        ModelAndView mav = new ModelAndView();

        // 사용자 확인
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");

        // 사용자 사물함 보유 여부 확인
        UsageLockerDTO usageLockerDTO = usageLockerService.getLocker(accountDTO);

        // 사물함 선택 여부 확인
        if (usageLockerDTO == null) {
            if (lockerNumber == null) {
                mav.setViewName("redirect:/inside/ticket/locker");
                return mav;
            }

            // 선택된 사물함 확인
            LockerDTO lockerDTO = lockerService.getByLockerNumber(Integer.parseInt(lockerNumber));
            if (lockerDTO == null || !lockerDTO.isUsable() || lockerSet.contains(lockerDTO.getLockerNumber())) {
                mav.setViewName("redirect:/inside/ticket/locker");
                return mav;
            }
            ticketMapService.putSelectedLocker(lockerDTO);

            // 사물함 결제중 정보 등록
            lockerSet.add(lockerDTO.getLockerNumber());

            // 사물함 정보 등록
            usageLockerDTO = new UsageLockerDTO();
            usageLockerDTO.setLockerDTO(lockerDTO);
        }

        // 선택된 사용권 확인
        LockerTicketDTO lockerTicketDTO = lockerTicketService.getById(ticket);
        if (lockerTicketDTO == null) {
            mav.setViewName("redirect:/inside/ticket/locker");
            return mav;
        }
        ticketMapService.putLockerTicket(lockerTicketDTO);

        mav.setViewName("redirect:/inside/payment");
		return mav;
    }
}
