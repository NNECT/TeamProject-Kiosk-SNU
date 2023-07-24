package com.KioskSNU.view.outside;

import com.KioskSNU.snu.service.UsageRoomService;
import com.KioskSNU.snu.service.UsageSeatService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OutsideSelectController {
    private final UsageSeatService usageSeatService;
    private final UsageRoomService usageRoomService;

    @RequestMapping("/outside/select")
    public ModelAndView process() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/select");

        Map<Integer, Integer> seatStatusMap = usageSeatService.getSeatStatusMap();
        mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

        Map<Integer, Integer> roomStatusMap = usageRoomService.getRoomStatusMap();
        mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

        return mav;
    }
}
