package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;

import java.util.List;

public interface UsageSeatService {
    UsageSeatDTO insert(UsageSeatDTO usageSeatDTO);
    UsageSeatDTO update(UsageSeatDTO usageSeatDTO);
    boolean delete(UsageSeatDTO usageSeatDTO);
    UsageSeatDTO getById(int id);
    List<UsageSeatDTO> getAll();
    List<UsageSeatDTO> getAllBySeat(SeatDTO seatDTO);
    List<UsageSeatDTO> getAllByAccount(AccountDTO accountDTO);
}
