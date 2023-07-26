package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsageCommutationTicketMapper {
    int insert(UsageCommutationTicketDTO usageCommutationTicketDTO);
    int update(UsageCommutationTicketDTO usageCommutationTicketDTO);
    int delete(UsageCommutationTicketDTO usageCommutationTicketDTO);
    UsageCommutationTicketDTO getById(int id);
    List<UsageCommutationTicketDTO> getAll();
    List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO);

    /**
     * 이전 연도의 같은 달부터 현재까지의 사용 기록을 가져온다.
     * @return UsageCommutationTicketDTO List
     */
    List<UsageCommutationTicketDTO> getAllBy1Year();
}
