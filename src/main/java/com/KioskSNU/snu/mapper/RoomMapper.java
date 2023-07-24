package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.RoomTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    int insert(RoomDTO roomDTO);
    int update(RoomDTO roomDTO);
    int delete(RoomDTO roomDTO);
    RoomDTO getById(int id);
    RoomDTO getByRoomNumber(int roomNumber);
    List<RoomDTO> getAll();
    List<RoomDTO> getAllByRoomType(RoomTypeDTO roomTypeDTO);
    List<RoomDTO> getAllByUsable(boolean usable);
}
