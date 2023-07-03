package com.KioskSNU.snu.dao;

import org.apache.ibatis.session.SqlSessionFactory;

public abstract class DAOTemplate {
    protected SqlSessionFactory sqlSessionFactory;
    public DAOTemplate(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
