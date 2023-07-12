package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class OutsideLogoutController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;

    @Autowired
    public OutsideLogoutController(
            Map<Integer, UsageSeatDTO> seatMap,
            Map<Integer, UsageRoomDTO> roomMap
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
    }

    @RequestMapping("/outside/logout")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/outside");

        String selectType = (String) session.getAttribute("selectType");
        if (selectType != null) {
            int selectNumber = (int) session.getAttribute("selectNumber");
            seatMap.remove(selectNumber);
            session.removeAttribute("selectType");
            session.removeAttribute("selectNumber");
        }

        return mav;
    }
}