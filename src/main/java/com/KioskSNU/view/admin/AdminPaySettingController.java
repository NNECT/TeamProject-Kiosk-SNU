package com.KioskSNU.view.admin;

import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.CommutationTicketService;
import com.KioskSNU.snu.service.LockerTicketService;
import com.KioskSNU.snu.service.RoomTypeService;
import com.KioskSNU.snu.service.TimeTicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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
            @RequestBody Map<String, String> timeTicketMap,
            @RequestBody Map<String, String> commutationTicketMap,
            @RequestBody Map<String, String> lockerTicketMap,
            @RequestBody Map<String, String> roomTypeMap
    ){
        ModelAndView mav = new ModelAndView();

        timeTicketMap.forEach((key, value) -> {
            TimeTicketDTO timeTicketDTO = timeTicketService.getById(Integer.parseInt(key));
            if (timeTicketDTO == null) return;
            timeTicketDTO.setPrice(Integer.parseInt(value));
            timeTicketService.update(timeTicketDTO);
        });

        commutationTicketMap.forEach((key, value) -> {
            CommutationTicketDTO commutationTicketDTO = commutationTicketService.getById(Integer.parseInt(key));
            if (commutationTicketDTO == null) return;
            commutationTicketDTO.setPrice(Integer.parseInt(value));
            commutationTicketService.update(commutationTicketDTO);
        });

        lockerTicketMap.forEach((key, value) -> {
            LockerTicketDTO lockerTicketDTO = lockerTicketService.getById(Integer.parseInt(key));
            if (lockerTicketDTO == null) return;
            lockerTicketDTO.setPrice(Integer.parseInt(value));
            lockerTicketService.update(lockerTicketDTO);
        });

        roomTypeMap.forEach((key, value) -> {
            RoomTypeDTO roomTypeDTO = roomTypeService.getById(Integer.parseInt(key));
            if (roomTypeDTO == null) return;
            roomTypeDTO.setPrice(Integer.parseInt(value));
            roomTypeService.update(roomTypeDTO);
        });

        mav.setViewName("admin/admin_main");
        return mav;
    }
}
