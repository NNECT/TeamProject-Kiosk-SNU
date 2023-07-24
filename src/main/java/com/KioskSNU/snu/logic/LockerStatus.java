package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;

import java.util.Map;

public interface LockerStatus {
    /**
     * 사물함 등록 여부 확인 메소드
     *
     * @param accountDTO 사용자
     * @return 사물함 등록 여부
     */
    boolean hasLocker(AccountDTO accountDTO);

    /**
     * 등록 사물함 Get 메소드
     *
     * @param accountDTO 사용자
     * @return 등록 사물함
     */
    UsageLockerDTO getLocker(AccountDTO accountDTO);

    /**
     * 현재 사물함 상태를 반환하는 메소드
     *
     * @return 사물함 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능)
     */
    Map<Integer, Integer> getLockerStatusMap();

    /**
     * 현재 사물함 상태를 반환하는 메소드
     *
     * @param accountDTO 정보를 얻는 사용자의 계정
     * @return 사물함 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능, 2: 현재 사용자의 사물함)
     */
    Map<Integer, Integer> getLockerStatusMap(AccountDTO accountDTO);
}
