package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.mapper.AccountMapper;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Qualifier("accountDAO")
    private final AccountMapper accountDAO;
    private final UsageCommutationTicketService usageCommutationTicketService;

    @Override
    public int insert(AccountDTO accountDTO) {
        return accountDAO.insert(accountDTO);
    }

    @Override
    public int update(AccountDTO accountDTO) {
        return accountDAO.update(accountDTO);
    }

    @Override
    public int delete(AccountDTO accountDTO) {
        return accountDAO.delete(accountDTO);
    }

    @Override
    public AccountDTO getById(int id) {
        return accountDAO.getById(id);
    }

    @Override
    public AccountDTO getByUsername(String username) {
        return accountDAO.getByUsername(username);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountDAO.getAll();
    }

    @Override
    public List<AccountDTO> getAllByPhoneNumber(String phoneNumber) {
        return accountDAO.getAllByPhoneNumber(phoneNumber);
    }

    @Override
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

    @Override
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
