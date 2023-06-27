package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.PointHistoryDTO;

import java.util.List;

public interface PointHistoryService {
    PointHistoryDTO insert(PointHistoryDTO pointHistoryDTO);
    PointHistoryDTO update(PointHistoryDTO pointHistoryDTO);
    boolean delete(PointHistoryDTO pointHistoryDTO);
    PointHistoryDTO getById(int id);
    List<PointHistoryDTO> getAll();
    List<PointHistoryDTO> getAllByAccount(AccountDTO accountDTO);
}
