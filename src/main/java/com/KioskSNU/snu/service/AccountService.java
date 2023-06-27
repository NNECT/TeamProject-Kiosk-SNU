package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO insert(AccountDTO accountDTO);
    AccountDTO update(AccountDTO accountDTO);
    boolean delete(AccountDTO accountDTO);
    AccountDTO getById(int id);
    AccountDTO getByUsername(String username);
    List<AccountDTO> getAll();
}
