package com.KioskSNU.view.admin;

import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.CommutationTicketService;
import com.KioskSNU.snu.service.LockerTicketService;
import com.KioskSNU.snu.service.RoomTypeService;
import com.KioskSNU.snu.service.TimeTicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminPaySettingController {

    private final TimeTicketService timeTicketService;
    private final CommutationTicketService commutationTicketService;
    private final LockerTicketService lockerTicketService;
    private final RoomTypeService roomTypeService;

    public AdminPaySettingController(TimeTicketService timeTicketService, CommutationTicketService commutationTicketService, LockerTicketService lockerTicketService, RoomTypeService roomTypeService) {
        this.timeTicketService = timeTicketService;
        this.commutationTicketService = commutationTicketService;
        this.lockerTicketService = lockerTicketService;
        this.roomTypeService = roomTypeService;
    }

    @GetMapping("/admin/adminpaysetting")
    public ModelAndView GetProcess(){

        ModelAndView mav = new ModelAndView();

        mav.addObject("timeTicket",timeTicketService.getAll());
        mav.addObject("commutationTicket",commutationTicketService.getAll());
        mav.addObject("lockerTicket", lockerTicketService.getAll());
        mav.addObject("roomTicket",roomTypeService.getAll());

        mav.setViewName("admin/admin_paySetting");
        return mav;

    }

    @PostMapping("/admin/adminpaysetting")
    public ModelAndView PostProcess(
            @RequestParam("timeTicketTime") int timeTicketTime,
            @RequestParam("timeTicketId") int timeTicketId,
            @RequestParam("hourlyPrice") int hourlyPrice,
            @RequestParam("commutationTicketDay") int commutationTicketDay,
            @RequestParam("commutationTicketId") int commutationTicketId,
            @RequestParam("monthlyPrice") int monthlyPrice,
            @RequestParam("lockerTicketDay") int lockerTicketDay,
            @RequestParam("lockerTicketId") int lockerTicketId,
            @RequestParam("lockerPrice") int lockerPrice,
            @RequestParam("roomTicketId") int roomTicketId,
            @RequestParam("roomTicketName") String roomTicketName,
            @RequestParam("roomPrice") int roomPrice
            ){

        ModelAndView mav = new ModelAndView();

        TimeTicketDTO timeTicketDTO = new TimeTicketDTO();
        timeTicketDTO.setTime(timeTicketTime);
        timeTicketDTO.setId(timeTicketId);
        timeTicketDTO.setPrice(hourlyPrice);
        timeTicketService.update(timeTicketDTO);

        System.out.println(timeTicketId);
        System.out.println(timeTicketTime);
        System.out.println(hourlyPrice);

        CommutationTicketDTO commutationTicketDTO = new CommutationTicketDTO();
        commutationTicketDTO.setDay(commutationTicketDay);
        commutationTicketDTO.setId(commutationTicketId);
        commutationTicketDTO.setPrice(monthlyPrice);
        commutationTicketService.update(commutationTicketDTO);

        LockerTicketDTO lockerTicketDTO = new LockerTicketDTO();
        lockerTicketDTO.setDay(lockerTicketDay);
        lockerTicketDTO.setId(lockerTicketId);
        lockerTicketDTO.setPrice(lockerPrice);
        lockerTicketService.update(lockerTicketDTO);

        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
        roomTypeDTO.setId(roomTicketId);
        roomTypeDTO.setName(roomTicketName);
        roomTypeDTO.setPrice(roomPrice);
        roomTypeService.update(roomTypeDTO);

        mav.setViewName("admin/admin_main");
        return mav;
    }
}
