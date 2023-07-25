package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 이전 연도의 같은 달부터 현재까지의 사용 기록을 가져온다.
     * @return UsageSeatDTO List
     */
    List<UsageSeatDTO> getAllBy1Year();
}
