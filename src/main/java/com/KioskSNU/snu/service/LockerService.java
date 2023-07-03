package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.LockerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface LockerService {
    int insert(LockerDTO lockerDTO);
    int update(LockerDTO lockerDTO);
    int delete(LockerDTO lockerDTO);
    LockerDTO getById(int id);
    List<LockerDTO> getAll();
    List<LockerDTO> getAllByUsable(boolean usable);
}
