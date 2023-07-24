package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.mapper.SeatMapper;
import com.KioskSNU.snu.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    @Qualifier("seatDAO")
    private final SeatMapper seatDAO;

    @Override
    public int insert(SeatDTO seatDTO) {
        return seatDAO.insert(seatDTO);
    }

    @Override
    public int update(SeatDTO seatDTO) {
        return seatDAO.update(seatDTO);
    }

    @Override
    public int delete(SeatDTO seatDTO) {
        return seatDAO.delete(seatDTO);
    }

    @Override
    public SeatDTO getById(int id) {
        return seatDAO.getById(id);
    }

    @Override
    public SeatDTO getBySeatNumber(int seatNumber) {
        return seatDAO.getBySeatNumber(seatNumber);
    }

    @Override
    public List<SeatDTO> getAll() {
        return seatDAO.getAll();
    }

    @Override
    public List<SeatDTO> getAllByUsable(boolean usable) {
        return seatDAO.getAllByUsable(usable);
    }
}
