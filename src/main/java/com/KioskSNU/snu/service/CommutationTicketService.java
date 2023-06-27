package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.CommutationTicketDTO;

import java.util.List;

public interface CommutationTicketService {
    CommutationTicketDTO insert(CommutationTicketDTO commutationTicketDTO);
    CommutationTicketDTO update(CommutationTicketDTO commutationTicketDTO);
    boolean delete(CommutationTicketDTO commutationTicketDTO);
    CommutationTicketDTO getById(int id);
    List<CommutationTicketDTO> getAll();
    List<CommutationTicketDTO> getAllByActive(boolean active);
}
