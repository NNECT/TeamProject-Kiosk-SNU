package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.RoomTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomService {
    int insert(RoomDTO roomDTO);
    int update(RoomDTO roomDTO);
    int delete(RoomDTO roomDTO);
    RoomDTO getById(int id);
    List<RoomDTO> getAll();
    List<RoomDTO> getAllByRoomType(RoomTypeDTO roomTypeDTO);
    List<RoomDTO> getAllByUsable(boolean usable);
}
