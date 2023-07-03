package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.PointHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface PointHistoryService {
    int insert(PointHistoryDTO pointHistoryDTO);
    int update(PointHistoryDTO pointHistoryDTO);
    int delete(PointHistoryDTO pointHistoryDTO);
    PointHistoryDTO getById(int id);
    List<PointHistoryDTO> getAll();
    List<PointHistoryDTO> getAllByAccount(AccountDTO accountDTO);
}
