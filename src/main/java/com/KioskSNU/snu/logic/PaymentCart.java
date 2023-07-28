package com.KioskSNU.snu.logic;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

public interface PaymentCart {
    /**
     * 카트에 담긴 티켓 정보를 모델 맵에 정리하여 담아 반환한다.
     * 각각 ticketList, timeList, priceList에 차례대로 담기고, price의 합을 totalPrice에 담는다.
     * @param session 사용자 세션
     */
    void setTicketMap(ModelMap modelMap, HttpSession session);
}
