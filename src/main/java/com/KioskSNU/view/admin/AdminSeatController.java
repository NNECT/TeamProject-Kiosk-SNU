package com.KioskSNU.view.admin;

import com.KioskSNU.api.RoomStatus;
import com.KioskSNU.api.SeatStatus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AdminSeatController {

    private final SeatStatus seatStatus;
    private final RoomStatus roomStatus;

    @Autowired
    public AdminSeatController(
            SeatStatus seatStatus,
            RoomStatus roomStatus
    ){
        this.seatStatus = seatStatus;
        this.roomStatus = roomStatus;
    }

    @RequestMapping("/admin/adminseat")
    public ModelAndView process(String type, Integer number){
        ModelAndView mav = new ModelAndView();

        //메인에서 좌석관리 눌러서 들어오는 경우
        if(type == null && number == null) {
            mav.setViewName("admin/admin_seat");

            Map<Integer, Integer> seatStatusMap = seatStatus.getSeatStatusMap();
            mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

            Map<Integer, Integer> roomStatusMap = roomStatus.getRoomStatusMap();
            mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

            return mav;
        }

        //좌석관리에서 좌석번호 눌러서 들어오는 경우
        //좌석관리에 대한 페이지 구현 필요


        return mav;
    }


}
