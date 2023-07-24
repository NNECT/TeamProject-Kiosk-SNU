package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsageLockerMapper {
    int insert(UsageLockerDTO usageLockerDTO);
    int update(UsageLockerDTO usageLockerDTO);
    int delete(UsageLockerDTO usageLockerDTO);
    UsageLockerDTO getById(int id);
    List<UsageLockerDTO> getAll();
    List<UsageLockerDTO> getAllByLocker(LockerDTO lockerDTO);
    List<UsageLockerDTO> getAllByAccount(AccountDTO accountDTO);
    List<UsageLockerDTO> getAllValidDate();
}
