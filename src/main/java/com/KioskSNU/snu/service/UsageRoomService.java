package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UsageRoomService {
    int insert(UsageRoomDTO usageRoomDTO);
    int update(UsageRoomDTO usageRoomDTO);
    int delete(UsageRoomDTO usageRoomDTO);
    UsageRoomDTO getById(int id);
    List<UsageRoomDTO> getAll();
    List<UsageRoomDTO> getAllByRoom(RoomDTO roomDTO);
    List<UsageRoomDTO> getAllByAccount(AccountDTO accountDTO);
}
