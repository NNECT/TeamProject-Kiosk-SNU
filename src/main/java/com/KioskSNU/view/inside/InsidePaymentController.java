package com.KioskSNU.view.inside;

import com.KioskSNU.interceptor.InsideLoginRequired;
import com.KioskSNU.secure.RSA;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class InsidePaymentController {
    private final RSA rsa;
    private final Map<String, Object> ticketMap;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;
    private final PaymentService paymentService;

    @RequestMapping("/inside/payment")
    @InsideLoginRequired
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        paymentService.setTicketMap(mav.getModelMap(), session);

        mav.addObject("publicKey", rsa.getPublicKey());
        mav.setViewName("inside/payment");
        return mav;
    }

    @RequestMapping("/inside/paymentSuccess")
    @InsideLoginRequired
    public ModelAndView successProcess(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 룸 이용권이면 이용권 정보 업데이트
        if (ticketMap.containsKey("room")) {
            int time = (Integer) ticketMap.get("room");

            // 자리 번호로 이용권 정보 가져오기
            int insideNumber = (Integer) session.getAttribute("insideNumber");
            UsageRoomDTO usageRoomDTO = roomMap.get(insideNumber);

            // 이용권 정보 업데이트
            usageRoomDTO.setEndDateTime(usageRoomDTO.getStartDateTime().plusHours(time));
        }

        // 장바구니 정보 삭제
        ticketMap.clear();

        mav.setViewName("inside/paymentSuccess");
        return mav;
    }

    @RequestMapping("/inside/cancel")
    @InsideLoginRequired
    public ModelAndView cancelProcess(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 장바구니 정보 삭제
        ticketMap.clear();

        mav.setViewName("redirect:/inside/menu");
        return mav;
    }
}
