package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.mapper.UsageCommutationTicketMapper;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UsageCommutationTicketServiceImpl implements UsageCommutationTicketService {
    @Qualifier("usageCommutationTicketDAO")
    private final UsageCommutationTicketMapper usageCommutationTicketDAO;

    @Override
    public int insert(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return usageCommutationTicketDAO.insert(usageCommutationTicketDTO);
    }

    @Override
    public int update(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return usageCommutationTicketDAO.update(usageCommutationTicketDTO);
    }

    @Override
    public int delete(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return usageCommutationTicketDAO.delete(usageCommutationTicketDTO);
    }

    @Override
    public UsageCommutationTicketDTO getById(int id) {
        return usageCommutationTicketDAO.getById(id);
    }

    @Override
    public List<UsageCommutationTicketDTO> getAll() {
        return usageCommutationTicketDAO.getAll();
    }

    @Override
    public List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO) {
        return usageCommutationTicketDAO.getAllByAccount(accountDTO);
    }

    @Override
    public List<UsageCommutationTicketDTO> getAllBy1Year() {
        return usageCommutationTicketDAO.getAllBy1Year();
    }

    @Override
    public Map<LocalDate, Integer> get1YearCommutationTicketUsers() {
        LinkedHashMap<LocalDate, Integer> datesMap = new LinkedHashMap<>();
        LinkedHashMap<LocalDate, Set<Integer>> tempMap = new LinkedHashMap<>();
        LocalDate ago1Year = LocalDate.now().minusYears(1).withDayOfMonth(1);

        // datesMap 1년치 초기화
        for (
                LocalDate date = ago1Year;
                !date.isAfter(LocalDate.now());
                date = date.plusMonths(1)
        ) {
            datesMap.put(date, 0);
            tempMap.put(date, new HashSet<>());
        }

        List<UsageCommutationTicketDTO> usageCommutationTicketDTOList = getAllBy1Year();
        usageCommutationTicketDTOList.forEach(usageCommutationTicketDTO -> {
            LocalDate start = usageCommutationTicketDTO.getStartDate();
            LocalDate end = usageCommutationTicketDTO.getEndDate();

            // 시작일이 1년 전 이전이면 1년 전으로, 종료일이 이번달 1일보다 미래이면 이번달 1일로
            if (start.isBefore(ago1Year)) start = ago1Year;
            if (end.isAfter(LocalDate.now().withDayOfMonth(1))) end = LocalDate.now().withDayOfMonth(1);

            // tempMap에 해당 월에 사용한 사람들 추가
            for (
                    LocalDate date = start.withDayOfMonth(1);
                    !date.isAfter(end);
                    date = date.plusMonths(1)
            ) {
                tempMap.get(date).add(usageCommutationTicketDTO.getAccount_id());
            }
        });

        // datesMap에 tempMap의 월별 사용자 수 추가
        datesMap.forEach((date, amount) -> {
            datesMap.put(date, tempMap.get(date).size());
        });

        return datesMap;
    }
}
