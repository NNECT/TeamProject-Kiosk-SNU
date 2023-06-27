package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;

import java.util.List;

public interface UsageLockerService {
    UsageLockerDTO insert(UsageLockerDTO usageLockerDTO);
    UsageLockerDTO update(UsageLockerDTO usageLockerDTO);
    boolean delete(UsageLockerDTO usageLockerDTO);
    UsageLockerDTO getById(int id);
    List<UsageLockerDTO> getAll();
    List<UsageLockerDTO> getAllByLocker(LockerDTO lockerDTO);
    List<UsageLockerDTO> getAllByAccount(AccountDTO accountDTO);
}
