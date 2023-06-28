package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.mapper.UsageCommutationTicketMapper;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageCommutationTicketDAO extends DAOTemplate implements UsageCommutationTicketService {
    @Autowired
    public UsageCommutationTicketDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    private static final String INSERT = "insert into usageCommutationTicket(account_id, endDate) values(?, ?)";
    private static final String UPDATE = "update usageCommutationTicket set endDate=? where id=?";
    private static final String DELETE = "delete from usageCommutationTicket where id=?";
    private static final String GET_BY_ID = "select (usageCommutationTicket.id, account.id, account.username, account.phoneNumber, account.password, account.point, account.remainTime, usageCommutationTicket.startDate, usageCommutationTicket.endDate) from usageCommutationTicket left join account on usageCommutationTicket.account_id = account.id where usageCommutationTicket.id=?";
    private static final String GET_ALL = "select (usageCommutationTicket.id, account.id, account.username, account.phoneNumber, account.password, account.point, account.remainTime, usageCommutationTicket.startDate, usageCommutationTicket.endDate) from usageCommutationTicket left join account on usageCommutationTicket.account_id = account.id order by usageCommutationTicket.endDate desc";
    private static final String GET_ALL_BY_ACCOUNT = "select (usageCommutationTicket.id, account.id, account.username, account.phoneNumber, account.password, account.point, account.remainTime, usageCommutationTicket.startDate, usageCommutationTicket.endDate) from usageCommutationTicket left join account on usageCommutationTicket.account_id = account.id where account.id=? order by usageCommutationTicket.endDate desc";

    @Override
    public UsageCommutationTicketDTO insert(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        Object[] args = {usageCommutationTicketDTO.getAccount_id(), usageCommutationTicketDTO.getEndDate()};
        if (jdbcTemplate.update(INSERT, args) == 1) {
            return getById(usageCommutationTicketDTO.getId());
        }
        return null;
    }

    @Override
    public UsageCommutationTicketDTO update(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        Object[] args = {usageCommutationTicketDTO.getEndDate(), usageCommutationTicketDTO.getId()};
        if (jdbcTemplate.update(UPDATE, args) == 1) {
            return getById(usageCommutationTicketDTO.getId());
        }
        return null;
    }

    @Override
    public boolean delete(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        Object[] args = {usageCommutationTicketDTO.getId()};
        return jdbcTemplate.update(DELETE, args) == 1;
    }

    @Override
    public UsageCommutationTicketDTO getById(int id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject(GET_BY_ID, args, new UsageCommutationTicketMapper());
    }

    @Override
    public List<UsageCommutationTicketDTO> getAll() {
        return jdbcTemplate.query(GET_ALL, new UsageCommutationTicketMapper());
    }

    @Override
    public List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO) {
        Object[] args = {accountDTO.getId()};
        return jdbcTemplate.query(GET_ALL_BY_ACCOUNT, args, new UsageCommutationTicketMapper());
    }
}
