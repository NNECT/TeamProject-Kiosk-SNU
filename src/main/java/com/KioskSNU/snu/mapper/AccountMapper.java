package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {
    int insert(AccountDTO accountDTO);
    int update(AccountDTO accountDTO);
    int delete(AccountDTO accountDTO);
    AccountDTO getById(int id);
    AccountDTO getByUsername(String username);
    List<AccountDTO> getAll();
    List<AccountDTO> getAllByPhoneNumber(String phoneNumber);
}
