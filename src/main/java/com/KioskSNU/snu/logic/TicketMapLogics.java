package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.LockerTicketDTO;
import com.KioskSNU.snu.dto.TimeTicketDTO;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

public interface TicketMapLogics {
    /**
     * 시간권 정보를 장바구니에 넣는다.
     * @param timeTicketDTO 시간권 정보
     */
    void putTimeTicket(TimeTicketDTO timeTicketDTO);

    /**
     * 시간권 정보를 장바구니에서 가져온다.
     * @return 시간권 정보
     */
    TimeTicketDTO getTimeTicket();

    /**
     * 정기권 정보를 장바구니에 넣는다.
     * @param commutationTicketDTO 정기권 정보
     */
    void putCommutationTicket(CommutationTicketDTO commutationTicketDTO);

    /**
     * 정기권 정보를 장바구니에서 가져온다.
     * @return 정기권 정보
     */
    CommutationTicketDTO getCommutationTicket();

    /**
     * 룸 사용시간 정보를 장바구니에 넣는다.
     * @param time 룸 사용시간
     */
    void putRoomTicket(int time);

    /**
     * 룸 사용시간 정보를 장바구니에서 가져온다.
     * @return 룸 사용시간
     */
    int getRoomTicket();

    /**
     * 선택한 사물함 정보를 장바구니에 넣는다.
     * @param lockerDTO 사물함 정보
     */
    void putSelectedLocker(LockerDTO lockerDTO);

    /**
     * 선택한 사물함 정보를 장바구니에서 가져온다.
     * @return 사물함 정보
     */
    LockerDTO getSelectedLocker();

    /**
     * 사물함 사용권 정보를 장바구니에 넣는다.
     * @param lockerTicketDTO 사물함 사용권 정보
     */
    void putLockerTicket(LockerTicketDTO lockerTicketDTO);

    /**
     * 사물함 사용권 정보를 장바구니에서 가져온다.
     * @return 사물함 사용권 정보
     */
    LockerTicketDTO getLockerTicket();

    /**
     * 카트에 담긴 티켓 정보를 모델 맵에 정리하여 담아 반환한다.
     * 각각 ticketList, timeList, priceList에 차례대로 담기고, price의 합을 totalPrice에 담는다.
     * @param modelMap 모델 맵
     * @param session 사용자 세션
     */
    void setTicketModel(ModelMap modelMap, HttpSession session);

    /**
     * 장바구니를 비운다.
     */
    void clear();
}
