package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dao.RoomTypeDAO;
import com.KioskSNU.snu.dto.RoomTypeDTO;
import com.KioskSNU.snu.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeDAO roomTypeDAO;

    @Override
    public int insert(RoomTypeDTO roomTypeDTO) {
        return roomTypeDAO.insert(roomTypeDTO);
    }

    @Override
    public int update(RoomTypeDTO roomTypeDTO) {
        return roomTypeDAO.update(roomTypeDTO);
    }

    @Override
    public int delete(RoomTypeDTO roomTypeDTO) {
        return roomTypeDAO.delete(roomTypeDTO);
    }

    @Override
    public RoomTypeDTO getById(int id) {
        return roomTypeDAO.getById(id);
    }

    @Override
    public List<RoomTypeDTO> getAll() {
        return roomTypeDAO.getAll();
    }
}
