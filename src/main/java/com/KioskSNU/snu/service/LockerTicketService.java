package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.LockerTicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LockerTicketService {
    int insert(LockerTicketDTO lockerTicketDTO);
    int update(LockerTicketDTO lockerTicketDTO);
    int delete(LockerTicketDTO lockerTicketDTO);
    LockerTicketDTO getById(int id);
    List<LockerTicketDTO> getAll();
    List<LockerTicketDTO> getAllByActive(boolean active);
}
