package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.RoomTypeDTO;

import java.util.List;

public interface RoomService {
    RoomDTO insert(RoomDTO roomDTO);
    RoomDTO update(RoomDTO roomDTO);
    boolean delete(RoomDTO roomDTO);
    RoomDTO getById(int id);
    List<RoomDTO> getAll();
    List<RoomDTO> getAllByRoomType(RoomTypeDTO roomTypeDTO);
    List<RoomDTO> getAllByUsable(boolean usable);
}
