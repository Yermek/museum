package com.businessproj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Kseniya
 */
@Service
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String testFunction() {
        return "hello world";
    }
}
