package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.mapper.LockerMapper;
import com.KioskSNU.snu.service.LockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LockerServiceImpl implements LockerService {
    @Qualifier("lockerDAO")
    private final LockerMapper lockerDAO;

    @Override
    public int insert(LockerDTO lockerDTO) {
        return lockerDAO.insert(lockerDTO);
    }

    @Override
    public int update(LockerDTO lockerDTO) {
        return lockerDAO.update(lockerDTO);
    }

    @Override
    public int delete(LockerDTO lockerDTO) {
        return lockerDAO.delete(lockerDTO);
    }

    @Override
    public LockerDTO getById(int id) {
        return lockerDAO.getById(id);
    }

    @Override
    public LockerDTO getByLockerNumber(int lockerNumber) {
        return lockerDAO.getByLockerNumber(lockerNumber);
    }

    @Override
    public List<LockerDTO> getAll() {
        return lockerDAO.getAll();
    }

    @Override
    public List<LockerDTO> getAllByUsable(boolean usable) {
        return lockerDAO.getAllByUsable(usable);
    }
}
