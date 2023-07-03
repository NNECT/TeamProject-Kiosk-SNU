package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UsageCommutationTicketService {
    int insert(UsageCommutationTicketDTO usageCommutationTicketDTO);
    int update(UsageCommutationTicketDTO usageCommutationTicketDTO);
    int delete(UsageCommutationTicketDTO usageCommutationTicketDTO);
    UsageCommutationTicketDTO getById(int id);
    List<UsageCommutationTicketDTO> getAll();
    List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO);
}
