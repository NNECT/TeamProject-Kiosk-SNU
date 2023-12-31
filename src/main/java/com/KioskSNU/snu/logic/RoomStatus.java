package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.AccountDTO;

import java.util.Map;

public interface RoomStatus {
    /**
     * 현재 룸 상태를 반환하는 메소드
     *
     * @return 룸 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능)
     */
    Map<Integer, Integer> getRoomStatusMap();

    /**
     * 현재 룸 상태를 반환하는 메소드
     *
     * @param accountDTO 정보를 얻는 사용자의 계정
     * @return 룸 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능, 2: 현재 사용자의 룸)
     */
    Map<Integer, Integer> getRoomStatusMap(AccountDTO accountDTO);
}
