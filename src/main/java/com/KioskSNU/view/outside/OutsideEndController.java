package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class OutsideEndController {
    private final HashMap<String, Object> ticketMap;
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;

    @RequestMapping("outside/end")
    public ModelAndView end(HttpSession session){
        ModelAndView mav = new ModelAndView();

        // 사용 시작
        String selectType = (String) session.getAttribute("selectType");
        switch (selectType) {
            case "seat": {
                int selectNumber = (int) session.getAttribute("selectNumber");
                seatMap.get(selectNumber).setStartDateTime(LocalDateTime.now());
                break;
            }
            case "room": {
                int selectNumber = (int) session.getAttribute("selectNumber");
                roomMap.get(selectNumber).setStartDateTime(LocalDateTime.now());
                roomMap.get(selectNumber).setEndDateTime(LocalDateTime.now().plusHours((int) ticketMap.get("room")));
                break;
            }
        }

        // 장바구니 정보 삭제
        session.removeAttribute("selectType");
        session.removeAttribute("selectNumber");
        ticketMap.clear();

        mav.setViewName("outside/end");
        return mav;
    }
}
