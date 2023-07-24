package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dao.UsageSeatDAO;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.UsageSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsageSeatServiceImpl implements UsageSeatService {
    private final UsageSeatDAO usageSeatDAO;

    @Override
    public int insert(UsageSeatDTO usageSeatDTO) {
        return usageSeatDAO.insert(usageSeatDTO);
    }

    @Override
    public int update(UsageSeatDTO usageSeatDTO) {
        return usageSeatDAO.update(usageSeatDTO);
    }

    @Override
    public int delete(UsageSeatDTO usageSeatDTO) {
        return usageSeatDAO.delete(usageSeatDTO);
    }

    @Override
    public UsageSeatDTO getById(int id) {
        return usageSeatDAO.getById(id);
    }

    @Override
    public List<UsageSeatDTO> getAll() {
        return usageSeatDAO.getAll();
    }

    @Override
    public List<UsageSeatDTO> getAllBySeat(SeatDTO seatDTO) {
        return usageSeatDAO.getAllBySeat(seatDTO);
    }

    @Override
    public List<UsageSeatDTO> getAllByAccount(AccountDTO accountDTO) {
        return usageSeatDAO.getAllByAccount(accountDTO);
    }
}
