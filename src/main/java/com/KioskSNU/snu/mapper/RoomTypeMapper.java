package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.RoomTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomTypeMapper {
    int insert(RoomTypeDTO roomTypeDTO);
    int update(RoomTypeDTO roomTypeDTO);
    int delete(RoomTypeDTO roomTypeDTO);
    RoomTypeDTO getById(int id);
    List<RoomTypeDTO> getAll();
}
