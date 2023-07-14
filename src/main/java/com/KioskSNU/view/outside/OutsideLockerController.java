package com.KioskSNU.view.outside;

/* Select파일과 비슷한 구조로 짰는데 26번줄 해결을 해야합니다.
    일단은 jsp파일을 볼 수 있게만 했습니다.
* */

import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.LockerService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class OutsideLockerController {
//    private final Map<Integer, UsageLockerDTO> LockerMap;
//
//    private final LockerService LockerService;
//
//
//    @Autowired
//    public OutsideLockerController(
//            ConcurrentHashMap<Integer, UsageLockerDTO> lockerMap,
//            LockerService LockerService
//    ) {
//        this.LockerMap = lockerMap;
//        this.LockerService = LockerService;
//    }

    @RequestMapping("/outside/locker")
    public String process() {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("outside/locker");
//
//        Map<Integer, Integer> LockerStatusMap = new HashMap<>();
//        LockerService.getAll().forEach(Locker -> LockerStatusMap.put(Locker.getLockerNumber(), Locker.isUsable() ? 1 : -1));
//        LockerMap.forEach((id, usage) -> LockerStatusMap.put(id, 0));
//        mav.addObject("LockerStatusMap", new Gson().toJson(LockerStatusMap));

        return "outside/locker";
    }
}
