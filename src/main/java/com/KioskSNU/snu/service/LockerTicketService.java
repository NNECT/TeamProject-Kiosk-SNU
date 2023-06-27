package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.LockerTicketDTO;

import java.util.List;

public interface LockerTicketService {
    LockerTicketDTO insert(LockerTicketDTO lockerTicketDTO);
    LockerTicketDTO update(LockerTicketDTO lockerTicketDTO);
    boolean delete(LockerTicketDTO lockerTicketDTO);
    LockerTicketDTO getById(int id);
    List<LockerTicketDTO> getAll();
    List<LockerTicketDTO> getAllByActive(boolean active);
}
