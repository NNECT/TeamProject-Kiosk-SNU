package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.TimeTicketDTO;
import com.KioskSNU.snu.mapper.TimeTicketMapper;
import com.KioskSNU.snu.service.TimeTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTicketServiceImpl implements TimeTicketService {
    @Qualifier("timeTicketDAO")
    private final TimeTicketMapper timeTicketDAO;

    @Override
    public int insert(TimeTicketDTO timeTicketDTO) {
        return timeTicketDAO.insert(timeTicketDTO);
    }

    @Override
    public int update(TimeTicketDTO timeTicketDTO) {
        return timeTicketDAO.update(timeTicketDTO);
    }

    @Override
    public int delete(TimeTicketDTO timeTicketDTO) {
        return timeTicketDAO.delete(timeTicketDTO);
    }

    @Override
    public TimeTicketDTO getById(int id) {
        return timeTicketDAO.getById(id);
    }

    @Override
    public List<TimeTicketDTO> getAll() {
        return timeTicketDAO.getAll();
    }

    @Override
    public List<TimeTicketDTO> getAllByActive(boolean active) {
        return timeTicketDAO.getAllByActive(active);
    }
}
