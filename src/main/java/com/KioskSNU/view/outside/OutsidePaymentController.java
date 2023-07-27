package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OutsidePaymentController {
    private final RSA rsa;
    private final HashMap<String, Object> ticketMap;
    private final RoomService roomService;

    @RequestMapping("/outside/payment")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        List<String> ticketList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        List<Integer> priceList = new ArrayList<>();

        ticketMap.forEach((key, value) -> {
            switch (key) {
                case "timeTicket":
                    ticketList.add("시간권");
                    TimeTicketDTO timeTicket = (TimeTicketDTO) value;
                    timeList.add(timeTicket.getTime() + "시간");
                    priceList.add(timeTicket.getPrice());
                    break;
                case "commutationTicket":
                    ticketList.add("정기권");
                    CommutationTicketDTO commutationTicket = (CommutationTicketDTO) value;
                    timeList.add(commutationTicket.getDay() + "일");
                    priceList.add(commutationTicket.getPrice());
                    break;
                case "room":
                    ticketList.add("룸");
                    timeList.add(value + "시간");
                    int roomNumber = (int) session.getAttribute("selectNumber");
                    RoomDTO roomDTO = roomService.getByRoomNumber(roomNumber);
                    priceList.add(((Integer) value) * roomDTO.getRoomType_price());
                    break;
                case "lockerTicket":
                    ticketList.add("사물함");
                    LockerTicketDTO lockerTicket = (LockerTicketDTO) value;
                    timeList.add(lockerTicket.getDay() + "일");
                    priceList.add(lockerTicket.getPrice());
                    break;
            }
        });

        mav.addObject("publicKey", rsa.getPublicKey());

        mav.addObject("ticketList", ticketList);
        mav.addObject("timeList", timeList);
        mav.addObject("priceList", priceList);
		mav.addObject("totalPrice", priceList.stream().mapToInt(Integer::intValue).sum());
        mav.setViewName("outside/payment");
        return mav;
    }

    @RequestMapping("/outside/paymentSuccess")
    public ModelAndView successProcess(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        session.removeAttribute("selectType");
        session.removeAttribute("selectNumber");

        mav.setViewName("outside/paymentSuccess");
        return mav;
    }
}
