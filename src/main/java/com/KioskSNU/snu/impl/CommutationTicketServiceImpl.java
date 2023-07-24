package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.mapper.CommutationTicketMapper;
import com.KioskSNU.snu.service.CommutationTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommutationTicketServiceImpl implements CommutationTicketService {
    @Qualifier("commutationTicketDAO")
    private final CommutationTicketMapper commutationTicketDAO;

    @Override
    public int insert(CommutationTicketDTO commutationTicketDTO) {
        return commutationTicketDAO.insert(commutationTicketDTO);
    }

    @Override
    public int update(CommutationTicketDTO commutationTicketDTO) {
		return commutationTicketDAO.update(commutationTicketDTO);
    }

    @Override
    public int delete(CommutationTicketDTO commutationTicketDTO) {
        return commutationTicketDAO.delete(commutationTicketDTO);
    }

    @Override
    public CommutationTicketDTO getById(int id) {
        return commutationTicketDAO.getById(id);
    }

    @Override
    public List<CommutationTicketDTO> getAll() {
        return commutationTicketDAO.getAll();
    }

    @Override
    public List<CommutationTicketDTO> getAllByActive(boolean active) {
        return commutationTicketDAO.getAllByActive(active);
    }
}
