package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.mapper.UsageRoomMapper;
import com.KioskSNU.snu.service.UsageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsageRoomServiceImpl implements UsageRoomService {
    @Qualifier("usageRoomDAO")
    private final UsageRoomMapper usageRoomDAO;

    @Override
    public int insert(UsageRoomDTO usageRoomDTO) {
        return usageRoomDAO.insert(usageRoomDTO);
    }

    @Override
    public int update(UsageRoomDTO usageRoomDTO) {
        return usageRoomDAO.update(usageRoomDTO);
    }

    @Override
    public int delete(UsageRoomDTO usageRoomDTO) {
        return usageRoomDAO.delete(usageRoomDTO);
    }

    @Override
    public UsageRoomDTO getById(int id) {
        return usageRoomDAO.getById(id);
    }

    @Override
    public List<UsageRoomDTO> getAll() {
        return usageRoomDAO.getAll();
    }

    @Override
    public List<UsageRoomDTO> getAllByRoom(RoomDTO roomDTO) {
        return usageRoomDAO.getAllByRoom(roomDTO);
    }

    @Override
    public List<UsageRoomDTO> getAllByAccount(AccountDTO accountDTO) {
        return usageRoomDAO.getAllByAccount(accountDTO);
    }
}
