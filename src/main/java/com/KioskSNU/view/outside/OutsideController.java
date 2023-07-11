package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/outside")
public class OutsideController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    private final RSA rsa;
    private final SHA sha;
    private final AccountService accountService;
    private final SeatService seatService;
    private final RoomService roomService;
    private final UsageSeatService usageSeatService;
    private final UsageRoomService usageRoomService;

    @Autowired
    public OutsideController(
            @Qualifier("seatMap") ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            @Qualifier("roomMap") ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            RSA rsa,
            SHA sha,
            AccountService accountService,
            SeatService seatService,
            RoomService roomService,
            UsageSeatService usageSeatService,
            UsageRoomService usageRoomService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.rsa = rsa;
        this.sha = sha;
        this.accountService = accountService;
        this.seatService = seatService;
        this.roomService = roomService;
        this.usageSeatService = usageSeatService;
        this.usageRoomService = usageRoomService;
    }

    @RequestMapping("")
    public ModelAndView outsideIndex() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/index");
        return mav;
    }

    @RequestMapping("/select")
    public ModelAndView outsideSelect() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/select");

        Map<Integer, Integer> seatStatusMap = new HashMap<>();
        seatService.getAll().forEach(seat -> seatStatusMap.put(seat.getSeatNumber(), seat.isUsable() ? 1 : -1));
        seatMap.forEach((id, usage) -> seatStatusMap.put(id, 0));
        mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

        Map<Integer, Integer> roomStatusMap = new HashMap<>();
        roomService.getAll().forEach(room -> roomStatusMap.put(room.getRoomNumber(), room.isUsable() ? 1 : -1));
        roomMap.forEach((id, usage) -> roomStatusMap.put(id, 0));
        mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

        return mav;
    }

    @GetMapping("/login")
    public ModelAndView outsideLogin(String type, int number, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/login");

        if (type == null) {
            mav.setViewName("redirect:/outside/select");
        } else if (type.equals("seat")) {
            UsageSeatDTO usage = new UsageSeatDTO();
            usage.setSeat_id(seatService.getBySeatNumber(number).getId());
            seatMap.put(number, usage);
            session.setAttribute("seatNumber", number);
        } else if (type.equals("room")) {
            UsageRoomDTO usage = new UsageRoomDTO();
            usage.setRoom_id(roomService.getByRoomNumber(number).getId());
            roomMap.put(number, usage);
            session.setAttribute("roomNumber", number);
        } else {
            mav.setViewName("redirect:/outside/select");
        }

        return mav;
    }

    @RequestMapping("/logout")
    public ModelAndView outsideLogout(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/outside");

        Integer seatNumber = (Integer) session.getAttribute("seatNumber");
        if (seatNumber != null) {
            seatMap.remove(seatNumber);
            session.removeAttribute("seatNumber");
        }

        Integer roomNumber = (Integer) session.getAttribute("roomNumber");
        if (roomNumber != null) {
            roomMap.remove(roomNumber);
            session.removeAttribute("roomNumber");
        }

        return mav;
    }
}
