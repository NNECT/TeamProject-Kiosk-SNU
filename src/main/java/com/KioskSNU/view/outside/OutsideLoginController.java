package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class OutsideLoginController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    private final RSA rsa;
    private final SHA sha;
    private final AccountService accountService;
    private final SeatService seatService;
    private final RoomService roomService;

    @Autowired
    public OutsideLoginController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            RSA rsa,
            SHA sha,
            AccountService accountService,
            SeatService seatService,
            RoomService roomService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.rsa = rsa;
        this.sha = sha;
        this.accountService = accountService;
        this.seatService = seatService;
        this.roomService = roomService;
    }

    @GetMapping("/outside/login")
    public ModelAndView getProcess(String type, int number, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/login");

        if (type == null) {
            mav.setViewName("redirect:/outside/logout");
        } else if (type.equals("seat")) {
            UsageSeatDTO usage = new UsageSeatDTO();
            usage.setSeat_id(seatService.getBySeatNumber(number).getId());
            seatMap.put(number, usage);
            session.setAttribute("selectType", type);
            session.setAttribute("selectNumber", number);
        } else if (type.equals("room")) {
            UsageRoomDTO usage = new UsageRoomDTO();
            usage.setRoom_id(roomService.getByRoomNumber(number).getId());
            roomMap.put(number, usage);
            session.setAttribute("selectType", type);
            session.setAttribute("selectNumber", number);
        } else {
            mav.setViewName("redirect:/outside/select");
        }

        return mav;
    }

    @PostMapping("/outside/login")
    public ModelAndView postProcess() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/login");
        return mav;
    }
}
