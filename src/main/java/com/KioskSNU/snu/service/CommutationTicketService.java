package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommutationTicketService {
    int insert(CommutationTicketDTO commutationTicketDTO);
    int update(CommutationTicketDTO commutationTicketDTO);
    int delete(CommutationTicketDTO commutationTicketDTO);
    CommutationTicketDTO getById(int id);
    List<CommutationTicketDTO> getAll();
    List<CommutationTicketDTO> getAllByActive(boolean active);
}
