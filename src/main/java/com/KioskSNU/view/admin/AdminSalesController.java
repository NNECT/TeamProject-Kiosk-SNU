package com.KioskSNU.view.admin;

import com.KioskSNU.snu.service.UsageLockerService;
import com.KioskSNU.snu.service.UsageRoomService;
import com.KioskSNU.snu.service.UsageSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminSalesController {
    private final UsageSeatService usageSeatService;
    private final UsageRoomService usageRoomService;
    private final UsageLockerService usageLockerService;

    @RequestMapping("/admin/sales")
    public ModelAndView sales() {
        System.out.println(1);
        ModelAndView mav = new ModelAndView();

        Map<LocalDate, Double> seatTimes = usageSeatService.get1YearTimes();
        System.out.println(2);
        List<String> seatTimesLabels = new ArrayList<>();
        System.out.println(3);
        List<Double> seatTimesData = new ArrayList<>();
        System.out.println(4);
        seatTimes.forEach((date, time) -> {
            seatTimesLabels.add(date.format(DateTimeFormatter.ofPattern("yyyy.MM")));
            seatTimesData.add(time);
        });
        System.out.println(5);

        mav.addObject("seatTimesLabels", seatTimesLabels);
        mav.addObject("seatTimesData", seatTimesData);
        mav.setViewName("admin/admin_sales");
        return mav;
    }
}
