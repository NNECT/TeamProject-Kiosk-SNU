package com.KioskSNU.snu.dao;

import org.mybatis.spring.SqlSessionTemplate;

public abstract class DAOTemplate {
    protected SqlSessionTemplate sqlSessionTemplate;
    public DAOTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
