package com.KioskSNU.snu.service;

import com.KioskSNU.snu.viewdto.SeatStatusDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeatStatusService {
    List<SeatStatusDTO> getAll();
}
