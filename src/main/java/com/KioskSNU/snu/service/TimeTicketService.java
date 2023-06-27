package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.TimeTicketDTO;

import java.util.List;

public interface TimeTicketService {
    TimeTicketDTO insert(TimeTicketDTO timeTicketDTO);
    TimeTicketDTO update(TimeTicketDTO timeTicketDTO);
    boolean delete(TimeTicketDTO timeTicketDTO);
    TimeTicketDTO getById(int id);
    List<TimeTicketDTO> getAll();
    List<TimeTicketDTO> getAllByActive(boolean active);
}
