package com.KioskSNU.view.admin;

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
public class AdminSeatController {
    private final UsageSeatService usageSeatService;
    private final UsageRoomService usageRoomService;

    @RequestMapping("/admin/adminseat")
    public ModelAndView process(String type, Integer number){
        ModelAndView mav = new ModelAndView();

        //메인에서 좌석관리 눌러서 들어오는 경우
        if(type == null && number == null) {
            mav.setViewName("admin/admin_seat");

            Map<Integer, Integer> seatStatusMap = usageSeatService.getSeatStatusMap();
            mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

            Map<Integer, Integer> roomStatusMap = usageRoomService.getRoomStatusMap();
            mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

            return mav;
        }

        //좌석관리에서 좌석번호 눌러서 들어오는 경우
        //좌석관리에 대한 페이지 구현 필요


        return mav;
    }


}
