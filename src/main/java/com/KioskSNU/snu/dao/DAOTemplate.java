package com.KioskSNU.snu.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DAOTemplate {
    protected JdbcTemplate jdbcTemplate;
    protected DAOTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
