package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsageSeatMapper {
    int insert(UsageSeatDTO usageSeatDTO);
    int update(UsageSeatDTO usageSeatDTO);
    int delete(UsageSeatDTO usageSeatDTO);
    UsageSeatDTO getById(int id);
    List<UsageSeatDTO> getAll();
    List<UsageSeatDTO> getAllBySeat(SeatDTO seatDTO);
    List<UsageSeatDTO> getAllByAccount(AccountDTO accountDTO);
}
