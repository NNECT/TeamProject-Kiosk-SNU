package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommutationTicketMapper {
    int insert(CommutationTicketDTO commutationTicketDTO);
    int update(CommutationTicketDTO commutationTicketDTO);
    int delete(CommutationTicketDTO commutationTicketDTO);
    CommutationTicketDTO getById(int id);
    List<CommutationTicketDTO> getAll();
    List<CommutationTicketDTO> getAllByActive(boolean active);
}
