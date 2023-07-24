package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.mapper.UsageCommutationTicketMapper;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsageCommutationTicketServiceImpl implements UsageCommutationTicketService {
    @Qualifier("usageCommutationTicketDAO")
    private final UsageCommutationTicketMapper usageCommutationTicketDAO;

    @Override
    public int insert(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return usageCommutationTicketDAO.insert(usageCommutationTicketDTO);
    }

    @Override
    public int update(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return usageCommutationTicketDAO.update(usageCommutationTicketDTO);
    }

    @Override
    public int delete(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return usageCommutationTicketDAO.delete(usageCommutationTicketDTO);
    }

    @Override
    public UsageCommutationTicketDTO getById(int id) {
        return usageCommutationTicketDAO.getById(id);
    }

    @Override
    public List<UsageCommutationTicketDTO> getAll() {
        return usageCommutationTicketDAO.getAll();
    }

    @Override
    public List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO) {
        return usageCommutationTicketDAO.getAllByAccount(accountDTO);
    }
}
