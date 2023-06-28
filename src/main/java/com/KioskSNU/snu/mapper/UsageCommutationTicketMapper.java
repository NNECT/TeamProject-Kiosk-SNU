package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsageCommutationTicketMapper implements RowMapper<UsageCommutationTicketDTO> {
    @Override
    public UsageCommutationTicketDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsageCommutationTicketDTO usageCommutationTicketDTO = new UsageCommutationTicketDTO();
        usageCommutationTicketDTO.setId(rs.getInt("usageCommutationTicket.id"));
        usageCommutationTicketDTO.setAccount_id(rs.getInt("account.id"));
        usageCommutationTicketDTO.setAccount_username(rs.getString("account.username"));
        usageCommutationTicketDTO.setAccount_phoneNumber(rs.getString("account.phoneNumber"));
        usageCommutationTicketDTO.setAccount_password(rs.getString("account.password"));
        usageCommutationTicketDTO.setAccount_point(rs.getInt("account.point"));
        usageCommutationTicketDTO.setAccount_remainTime(rs.getInt("account.remainTime"));
        usageCommutationTicketDTO.setStartDate(rs.getDate("usageCommutationTicket.startDate").toLocalDate());
        usageCommutationTicketDTO.setEndDate(rs.getDate("usageCommutationTicket.endDate").toLocalDate());
        return usageCommutationTicketDTO;
    }
}
