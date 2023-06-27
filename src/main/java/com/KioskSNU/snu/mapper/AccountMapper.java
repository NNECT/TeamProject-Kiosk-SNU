package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<AccountDTO> {
    @Override
    public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(rs.getInt("id"));
        accountDTO.setUsername(rs.getString("username"));
        accountDTO.setPassword(rs.getString("password"));
        accountDTO.setPhoneNumber(rs.getString("phone_number"));
        accountDTO.setPoint(rs.getInt("point"));
        accountDTO.setRemainTime(rs.getInt("remainTime"));
        return accountDTO;
    }
}
