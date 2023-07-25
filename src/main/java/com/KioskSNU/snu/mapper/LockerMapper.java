package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.LockerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface LockerMapper {
    int insert(LockerDTO lockerDTO);
    int update(LockerDTO lockerDTO);
    int delete(LockerDTO lockerDTO);
    LockerDTO getById(int id);
    LockerDTO getByLockerNumber(int lockerNumber);
    List<LockerDTO> getAll();
    List<LockerDTO> getAllByUsable(boolean usable);
}
