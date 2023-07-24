package com.KioskSNU.api;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountCommutationTicketCheck {
    private final UsageCommutationTicketService usageCommutationTicketService;

    /**
     * 유효 정기권 소지 여부 확인 메소드
     * @param accountDTO 사용자
     * @return 유효 정기권 소지 여부
     */
    public boolean hasCommutationTicket(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return false;

        // 티켓 확인
        List<UsageCommutationTicketDTO> usageCommutationTicketDTOList = usageCommutationTicketService.getAllByAccount(accountDTO);
        if (usageCommutationTicketDTOList.isEmpty()) return false;

        // 티켓 유효기간 확인
        UsageCommutationTicketDTO usageCommutationTicketDTO = usageCommutationTicketDTOList.get(0);
        return !usageCommutationTicketDTO.getEndDate().isBefore(LocalDate.now());
    }

    /**
     * 유효 정기권 Get 메소드
     * @param accountDTO 사용자
     * @return 유효 정기권
     */
    public UsageCommutationTicketDTO getCommutationTicket(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return null;

        // 티켓 확인
        List<UsageCommutationTicketDTO> usageCommutationTicketDTOList = usageCommutationTicketService.getAllByAccount(accountDTO);
        if (usageCommutationTicketDTOList.isEmpty()) return null;

        // 티켓 유효기간 확인
        UsageCommutationTicketDTO usageCommutationTicketDTO = usageCommutationTicketDTOList.get(0);
        if (usageCommutationTicketDTO.getEndDate().isBefore(LocalDate.now())) return null;

        return usageCommutationTicketDTO;
    }
}
