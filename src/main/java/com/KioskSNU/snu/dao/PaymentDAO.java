package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.PaymentDTO;
import com.KioskSNU.snu.mapper.PaymentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PaymentDAO extends SqlSessionDaoSupport implements PaymentMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(PaymentDTO paymentDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.PaymentMapper.insert", paymentDTO);
    }

    @Override
    public int update(PaymentDTO paymentDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.PaymentMapper.update", paymentDTO);
    }

    @Override
    public int delete(PaymentDTO paymentDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.PaymentMapper.delete", paymentDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public PaymentDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.PaymentMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.PaymentMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.PaymentMapper.getAllByAccount", accountDTO);
    }
}
