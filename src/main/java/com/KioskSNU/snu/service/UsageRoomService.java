package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;

import java.util.List;

public interface UsageRoomService {
    UsageRoomDTO insert(UsageRoomDTO usageRoomDTO);
    UsageRoomDTO update(UsageRoomDTO usageRoomDTO);
    boolean delete(UsageRoomDTO usageRoomDTO);
    UsageRoomDTO getById(int id);
    List<UsageRoomDTO> getAll();
    List<UsageRoomDTO> getAllByRoom(RoomDTO roomDTO);
    List<UsageRoomDTO> getAllByAccount(AccountDTO accountDTO);
}
