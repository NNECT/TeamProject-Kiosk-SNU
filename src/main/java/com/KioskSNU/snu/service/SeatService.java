package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.SeatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeatService {
    int insert(SeatDTO seatDTO);
    int update(SeatDTO seatDTO);
    int delete(SeatDTO seatDTO);
    SeatDTO getById(int id);
    SeatDTO getBySeatNumber(int seatNumber);
    List<SeatDTO> getAll();
    List<SeatDTO> getAllByUsable(boolean usable);
}
