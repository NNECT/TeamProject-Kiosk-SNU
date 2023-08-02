package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.TicketMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class OutsideLogoutController {
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;
    private final Set<Integer> lockerSet;
    private final TicketMapService ticketMapService;

    @RequestMapping("/outside/logout")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/outside");

        // 자리 선택이 안 되어 있으면 즉시 처리
        if (session.getAttribute("selectType") == null) {
            session.invalidate();
            return mav;
        }

        // 자리 선택이 되어 있으면 자리 정보 삭제
        switch ((String) session.getAttribute("selectType")) {
            case "seat":
                seatMap.remove((int) session.getAttribute("selectNumber"));
                break;
            case "room":
                roomMap.remove((int) session.getAttribute("selectNumber"));
                break;
        }

        // 사물함 선택이 되어 있으면 사물함 정보 삭제
        LockerDTO lockerDTO = ticketMapService.getSelectedLocker();
        if (lockerDTO != null) {
            lockerSet.remove(lockerDTO.getLockerNumber());
        }

        session.invalidate();

        return mav;
    }
}
