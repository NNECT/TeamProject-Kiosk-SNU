package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.mapper.AccountMapper;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountDAO extends DAOTemplate implements AccountService {
    @Autowired
    public AccountDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private static final String INSERT = "insert into account(username, phoneNumber, password) values(?, ?, ?)";
    private static final String UPDATE = "update account set phoneNumber=?, password=?, point=?, remainTime=? where id=?";
    private static final String DELETE = "delete from account where id=?";
    private static final String GET_BY_ID = "select * from account where id=?";
    private static final String GET_BY_USERNAME = "select * from account where username=?";
    private static final String GET_ALL = "select * from account";

    @Override
    public AccountDTO insert(AccountDTO accountDTO) {
        Object[] args = {accountDTO.getUsername(), accountDTO.getPhoneNumber(), accountDTO.getPassword()};
        if (jdbcTemplate.update(INSERT, args) == 1) {
            return getByUsername(accountDTO.getUsername());
        }
        return null;
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        Object[] args = {accountDTO.getPhoneNumber(), accountDTO.getPassword(), accountDTO.getPoint(), accountDTO.getRemainTime(), accountDTO.getId()};
        if (jdbcTemplate.update(UPDATE, args) == 1) {
            return getById(accountDTO.getId());
        }
        return null;
    }

    @Override
    public boolean delete(AccountDTO accountDTO) {
        Object[] args = {accountDTO.getId()};
        return jdbcTemplate.update(DELETE, args) == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDTO getById(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject(GET_BY_ID, args, new AccountMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDTO getByUsername(String username) {
        Object[] args = {username};
        return jdbcTemplate.queryForObject(GET_BY_USERNAME, args, new AccountMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> getAll() {
        return jdbcTemplate.query(GET_ALL, new AccountMapper());
    }
}
