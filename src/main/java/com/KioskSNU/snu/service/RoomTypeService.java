package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.RoomTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomTypeService {
    int insert(RoomTypeDTO roomTypeDTO);
    int update(RoomTypeDTO roomTypeDTO);
    int delete(RoomTypeDTO roomTypeDTO);
    RoomTypeDTO getById(int id);
    RoomTypeDTO getByName(String name);
    List<RoomTypeDTO> getAll();
}
