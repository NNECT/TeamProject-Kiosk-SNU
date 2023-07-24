package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.UsageLockerService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OutsideLockerController {
    private final UsageLockerService usageLockerService;

    @RequestMapping("/outside/locker")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 사용자 확인
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("accountDTO");

        // 사물함 상태 확인
        Map<Integer, Integer> lockerStatusMap = usageLockerService.getLockerStatusMap(accountDTO);
        mav.addObject("lockerStatusMap", new Gson().toJson(lockerStatusMap));
        mav.setViewName("outside/lockerTicket");
        return mav;
    }
}
