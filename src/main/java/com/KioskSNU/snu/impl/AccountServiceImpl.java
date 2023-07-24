package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dao.AccountDAO;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;

    @Override
    public int insert(AccountDTO accountDTO) {
        return accountDAO.insert(accountDTO);
    }

    @Override
    public int update(AccountDTO accountDTO) {
        return accountDAO.update(accountDTO);
    }

    @Override
    public int delete(AccountDTO accountDTO) {
        return accountDAO.delete(accountDTO);
    }

    @Override
    public AccountDTO getById(int id) {
        return accountDAO.getById(id);
    }

    @Override
    public AccountDTO getByUsername(String username) {
        return accountDAO.getByUsername(username);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountDAO.getAll();
    }

    @Override
    public List<AccountDTO> getAllByPhoneNumber(String phoneNumber) {
        return accountDAO.getAllByPhoneNumber(phoneNumber);
    }

    @Override
    public int getCount() {
        return accountDAO.getCount();
    }

    @Override
    public List<AccountDTO> selectPage(Map<String, Integer> map) {
        return accountDAO.selectPage(map);
    }
}
