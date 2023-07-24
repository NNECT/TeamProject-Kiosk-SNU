package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dao.UsageLockerDAO;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.UsageLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsageLockerServiceImpl implements UsageLockerService {
    private final UsageLockerDAO usageLockerDAO;

    @Override
    public int insert(UsageLockerDTO usageLockerDTO) {
        return usageLockerDAO.insert(usageLockerDTO);
    }

    @Override
    public int update(UsageLockerDTO usageLockerDTO) {
        return usageLockerDAO.update(usageLockerDTO);
    }

    @Override
    public int delete(UsageLockerDTO usageLockerDTO) {
        return usageLockerDAO.delete(usageLockerDTO);
    }

    @Override
    public UsageLockerDTO getById(int id) {
        return usageLockerDAO.getById(id);
    }

    @Override
    public List<UsageLockerDTO> getAll() {
        return usageLockerDAO.getAll();
    }

    @Override
    public List<UsageLockerDTO> getAllByLocker(LockerDTO lockerDTO) {
        return usageLockerDAO.getAllByLocker(lockerDTO);
    }

    @Override
    public List<UsageLockerDTO> getAllByAccount(AccountDTO accountDTO) {
        return usageLockerDAO.getAllByAccount(accountDTO);
    }

    @Override
    public List<UsageLockerDTO> getAllValidDate() {
        return usageLockerDAO.getAllValidDate();
    }
}
