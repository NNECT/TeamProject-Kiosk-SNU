package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.RoomTypeDTO;
import com.KioskSNU.snu.mapper.RoomMapper;
import com.KioskSNU.snu.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    @Qualifier("roomDAO")
    private final RoomMapper roomDAO;

    @Override
    public int insert(RoomDTO roomDTO) {
        return roomDAO.insert(roomDTO);
    }

    @Override
    public int update(RoomDTO roomDTO) {
        return roomDAO.update(roomDTO);
    }

    @Override
    public int delete(RoomDTO roomDTO) {
        return roomDAO.delete(roomDTO);
    }

    @Override
    public RoomDTO getById(int id) {
        return roomDAO.getById(id);
    }

    @Override
    public RoomDTO getByRoomNumber(int roomNumber) {
        return roomDAO.getByRoomNumber(roomNumber);
    }

    @Override
    public List<RoomDTO> getAll() {
        return roomDAO.getAll();
    }

    @Override
    public List<RoomDTO> getAllByRoomType(RoomTypeDTO roomTypeDTO) {
        return roomDAO.getAllByRoomType(roomTypeDTO);
    }

    @Override
    public List<RoomDTO> getAllByUsable(boolean usable) {
        return roomDAO.getAllByUsable(usable);
    }
}
