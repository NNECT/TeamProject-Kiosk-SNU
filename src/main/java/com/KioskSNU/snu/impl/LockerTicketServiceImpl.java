package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dao.LockerTicketDAO;
import com.KioskSNU.snu.dto.LockerTicketDTO;
import com.KioskSNU.snu.service.LockerTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LockerTicketServiceImpl implements LockerTicketService {
    private final LockerTicketDAO lockerTicketDAO;

    @Override
    public int insert(LockerTicketDTO lockerTicketDTO) {
        return lockerTicketDAO.insert(lockerTicketDTO);
    }

    @Override
    public int update(LockerTicketDTO lockerTicketDTO) {
        return lockerTicketDAO.update(lockerTicketDTO);
    }

    @Override
    public int delete(LockerTicketDTO lockerTicketDTO) {
        return lockerTicketDAO.delete(lockerTicketDTO);
    }

    @Override
    public LockerTicketDTO getById(int id) {
        return lockerTicketDAO.getById(id);
    }

    @Override
    public List<LockerTicketDTO> getAll() {
        return lockerTicketDAO.getAll();
    }

    @Override
    public List<LockerTicketDTO> getAllByActive(boolean active) {
        return lockerTicketDAO.getAllByActive(active);
    }
}
