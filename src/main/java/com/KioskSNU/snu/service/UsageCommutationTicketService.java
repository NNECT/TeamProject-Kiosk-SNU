package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;

import java.util.List;

public interface UsageCommutationTicketService {
    UsageCommutationTicketDTO insert(UsageCommutationTicketDTO usageCommutationTicketDTO);
    UsageCommutationTicketDTO update(UsageCommutationTicketDTO usageCommutationTicketDTO);
    boolean delete(UsageCommutationTicketDTO usageCommutationTicketDTO);
    UsageCommutationTicketDTO getById(int id);
    List<UsageCommutationTicketDTO> getAll();
    List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO);
}
