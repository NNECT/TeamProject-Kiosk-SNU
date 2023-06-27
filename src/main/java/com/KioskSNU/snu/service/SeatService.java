package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.SeatDTO;

import java.util.List;

public interface SeatService {
    SeatDTO insert(SeatDTO seatDTO);
    SeatDTO update(SeatDTO seatDTO);
    boolean delete(SeatDTO seatDTO);
    SeatDTO getById(int id);
    List<SeatDTO> getAll();
    List<SeatDTO> getAllByUsable(boolean usable);
}
