package com.KioskSNU.view.outside;

import com.KioskSNU.api.RoomStatus;
import com.KioskSNU.api.SeatStatus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class OutsideSelectController {
    private final SeatStatus seatStatus;
    private final RoomStatus roomStatus;

    @Autowired
    public OutsideSelectController(
            SeatStatus seatStatus,
            RoomStatus roomStatus
    ) {
        this.seatStatus = seatStatus;
        this.roomStatus = roomStatus;
    }

    @RequestMapping("/outside/select")
    public ModelAndView process() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/select");

        Map<Integer, Integer> seatStatusMap = seatStatus.getSeatStatusMap();
        mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

        Map<Integer, Integer> roomStatusMap = roomStatus.getRoomStatusMap();
        mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

        return mav;
    }
}
