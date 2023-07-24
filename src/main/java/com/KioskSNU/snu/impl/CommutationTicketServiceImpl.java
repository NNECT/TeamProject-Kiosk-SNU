package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.service.CommutationTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommutationTicketServiceImpl implements CommutationTicketService {
    private final CommutationTicketService commutationTicketService;

    @Override
    public int insert(CommutationTicketDTO commutationTicketDTO) {
        return commutationTicketService.insert(commutationTicketDTO);
    }

    @Override
    public int update(CommutationTicketDTO commutationTicketDTO) {
		return commutationTicketService.update(commutationTicketDTO);
    }

    @Override
    public int delete(CommutationTicketDTO commutationTicketDTO) {
        return commutationTicketService.delete(commutationTicketDTO);
    }

    @Override
    public CommutationTicketDTO getById(int id) {
        return commutationTicketService.getById(id);
    }

    @Override
    public List<CommutationTicketDTO> getAll() {
        return commutationTicketService.getAll();
    }

    @Override
    public List<CommutationTicketDTO> getAllByActive(boolean active) {
        return commutationTicketService.getAllByActive(active);
    }
}
