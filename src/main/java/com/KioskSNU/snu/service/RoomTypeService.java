package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.RoomTypeDTO;

import java.util.List;

public interface RoomTypeService {
    RoomTypeDTO insert(RoomTypeDTO roomTypeDTO);
    RoomTypeDTO update(RoomTypeDTO roomTypeDTO);
    boolean delete(RoomTypeDTO roomTypeDTO);
    RoomTypeDTO getById(int id);
    RoomTypeDTO getByName(String name);
    List<RoomTypeDTO> getAll();
}
