package com.KioskSNU.common;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AccountCommutationCheck {
    private final AccountService accountService;
    private final UsageCommutationTicketService usageCommutationTicketService;

    @Autowired
    public AccountCommutationCheck(
            AccountService accountService,
            UsageCommutationTicketService usageCommutationTicketService
    ) {
        this.accountService = accountService;
        this.usageCommutationTicketService = usageCommutationTicketService;
    }

    /**
     * 유효 정기권 소지 여부 확인 메소드
     * @param account_id 사용자 id
     * @return 유효 정기권 소지 여부
     */
    public boolean hasCommutationTicket(int account_id) {
        // 사용자 확인
        AccountDTO accountDTO = accountService.getById(account_id);
        if (accountDTO == null) return false;

        // 티켓 확인
        List<UsageCommutationTicketDTO> usageCommutationTicketDTOList = usageCommutationTicketService.getAllByAccount(accountDTO);
        if (usageCommutationTicketDTOList.isEmpty()) return false;

        // 티켓 유효기간 확인
        UsageCommutationTicketDTO usageCommutationTicketDTO = usageCommutationTicketDTOList.get(0);
        return !usageCommutationTicketDTO.getEndDate().isBefore(LocalDate.now());
    }
}
