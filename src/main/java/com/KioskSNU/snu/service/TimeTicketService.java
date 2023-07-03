package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.TimeTicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimeTicketService {
    int insert(TimeTicketDTO timeTicketDTO);
    int update(TimeTicketDTO timeTicketDTO);
    int delete(TimeTicketDTO timeTicketDTO);
    TimeTicketDTO getById(int id);
    List<TimeTicketDTO> getAll();
    List<TimeTicketDTO> getAllByActive(boolean active);
}
