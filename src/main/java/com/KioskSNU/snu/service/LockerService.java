package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.LockerDTO;

import java.util.List;

public interface LockerService {
    LockerDTO insert(LockerDTO lockerDTO);
    LockerDTO update(LockerDTO lockerDTO);
    boolean delete(LockerDTO lockerDTO);
    LockerDTO getById(int id);
    List<LockerDTO> getAll();
    List<LockerDTO> getAllByUsable(boolean usable);
}
