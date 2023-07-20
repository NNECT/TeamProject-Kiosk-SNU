package com.KioskSNU.view.outside;

import com.KioskSNU.api.SeatStatus;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.SeatService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class OutsideSelectController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    private final SeatStatus seatStatus;
    private final SeatService seatService;
    private final RoomService roomService;

    @Autowired
    public OutsideSelectController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            SeatStatus seatStatus,
            SeatService seatService,
            RoomService roomService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.seatStatus = seatStatus;
        this.seatService = seatService;
        this.roomService = roomService;
    }

    @RequestMapping("/outside/select")
    public ModelAndView process() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/select");

        Map<Integer, Integer> seatStatusMap = seatStatus.getSeatStatusMap();
        mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

        Map<Integer, Integer> roomStatusMap = new HashMap<>();
        roomService.getAll().forEach(room -> roomStatusMap.put(room.getRoomNumber(), room.isUsable() ? 1 : -1));
        roomMap.forEach((id, usage) -> roomStatusMap.put(id, 0));
        mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

        return mav;
    }
}
