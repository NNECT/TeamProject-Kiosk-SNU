package com.KioskSNU.api;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RoomStatus {
    private final Map<Integer, UsageRoomDTO> roomMap;
    private final RoomService roomService;

    /**
     * 현재 룸 상태를 반환하는 메소드
     * @return 룸 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능)
     */
    public Map<Integer, Integer> getRoomStatusMap() {
        return getRoomStatusMap(null);
    }

    /**
     * 현재 룸 상태를 반환하는 메소드
     * @param accountDTO 정보를 얻는 사용자의 계정
     * @return 룸 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능, 2: 현재 사용자의 룸)
     */
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
