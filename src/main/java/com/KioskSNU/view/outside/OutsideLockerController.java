package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.LockerService;
import com.KioskSNU.snu.service.UsageLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OutsideLockerController {
    private final LockerService lockerService;
    private final UsageLockerService usageLockerService;

    @RequestMapping("/outside/locker")
    public ModelAndView process() {
        ModelAndView mav = new ModelAndView();

        Map<Integer, Integer> lockerStatusMap = new HashMap<>();
        lockerService.getAll().forEach(locker -> {
            if (!locker.isUsable()) {
                lockerStatusMap.put(locker.getLockerNumber(), -1);
                return;
            }

            List<UsageLockerDTO> usageLockerList = usageLockerService.getAllByLocker(locker);
            if (!usageLockerList.isEmpty()) {
                UsageLockerDTO latest = usageLockerList.get(0);
                if (latest.getEndDate().equals(LocalDate.now()) || latest.getEndDate().isAfter(LocalDate.now())) {
                    lockerStatusMap.put(locker.getLockerNumber(), 0);
                    return;
                }
            }

            lockerStatusMap.put(locker.getLockerNumber(), 1);
        });

        mav.addObject("lockerStatusMap", lockerStatusMap);

        mav.setViewName("outside/locker");
        return mav;
    }
}
