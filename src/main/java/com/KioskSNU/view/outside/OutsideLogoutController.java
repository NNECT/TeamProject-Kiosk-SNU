package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class OutsideLogoutController {
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;

    @RequestMapping("/outside/logout")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/outside");

        switch ((String) session.getAttribute("selectType")) {
            case "seat":
                seatMap.remove((int) session.getAttribute("selectNumber"));
                break;
            case "room":
                roomMap.remove((int) session.getAttribute("selectNumber"));
                break;
        }

        session.invalidate();

        return mav;
    }
}
