package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;

public interface AccountCommutationTicketCheck {
    /**
     * 유효 정기권 소지 여부 확인 메소드
     *
     * @param accountDTO 사용자
     * @return 유효 정기권 소지 여부
     */
    boolean hasCommutationTicket(AccountDTO accountDTO);

    /**
     * 유효 정기권 Get 메소드
     *
     * @param accountDTO 사용자
     * @return 유효 정기권
     */
    UsageCommutationTicketDTO getCommutationTicket(AccountDTO accountDTO);
}
