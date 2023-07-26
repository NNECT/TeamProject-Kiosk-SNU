package com.KioskSNU.view.admin;

import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.UsageRoomService;
import com.KioskSNU.snu.service.UsageSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminSeatReportController {
    private final UsageSeatService usageSeatService;
    private final UsageRoomService usageRoomService;

    @RequestMapping("/admin/adminseatreport")
    public ModelAndView process(String type, Integer number){
        ModelAndView mav = new ModelAndView();

        if(type.equals("seat")){
            mav.addObject("type",type);
            mav.addObject("number",number);
            mav.addObject("seatReport",usageSeatService.getAll());
            mav.setViewName("admin/admin_seatReport");

        }else if(type.equals("room")){
            mav.addObject("type",type);
            mav.addObject("number",number);
            mav.addObject("roomReport",usageRoomService.getAll());
            mav.setViewName("admin/admin_roomReport");

        }

        return mav;
    }

}

