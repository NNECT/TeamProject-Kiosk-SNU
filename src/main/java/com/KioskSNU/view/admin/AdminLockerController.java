package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.LockerService;
import com.KioskSNU.snu.service.UsageLockerService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminLockerController {
    private final UsageLockerService usageLockerService;
    private final LockerService lockerService;

    // 사물함 전체
    @RequestMapping("/admin/adminlocker")
    @AdminLoginRequired
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        Map<Integer, Integer> lockerStatusMap = usageLockerService.getLockerStatusMap();
        mav.addObject("lockerStatusMap", new Gson().toJson(lockerStatusMap));
        mav.setViewName("admin/admin_locker");
        return mav;
    }

/*    @RequestMapping("/admin/adminLockerRecord")
    @AdminLoginRequired
    public ModelAndView previousRecord(UsageLockerDTO usageLockerDTO){

        ModelAndView mav = new ModelAndView();
        mav.addObject("record",lockerService.getByLockerNumber(usageLockerDTO.getLocker_lockerNumber()));
        mav.setViewName("admin/admin_locker");
        return mav;
    }*/

}
