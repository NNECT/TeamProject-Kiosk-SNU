package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.mapper.UsageRoomMapper;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.UsageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UsageRoomServiceImpl implements UsageRoomService {
    @Qualifier("usageRoomDAO")
    private final UsageRoomMapper usageRoomDAO;
    private final RoomService roomService;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;

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

    @Override
    public Map<Integer, Integer> getRoomStatusMap() {
        return getRoomStatusMap(null);
    }

    @Override
    public Map<Integer, Integer> getRoomStatusMap(AccountDTO accountDTO) {
        Map<Integer, Integer> roomStatusMap = new HashMap<>();

        // 사용 불가 룸 처리
        roomService.getAll().forEach(room -> roomStatusMap.put(room.getRoomNumber(), room.isUsable() ? 1 : -1));

        // 사용중 룸 처리
        roomMap.forEach((id, usage) -> {
            if (accountDTO != null && accountDTO.getId() == usage.getAccount_id()) {
                roomStatusMap.put(id, 2);
            } else {
                roomStatusMap.put(id, 0);
            }
        });

        return roomStatusMap;
    }
}
