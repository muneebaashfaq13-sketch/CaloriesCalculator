package com.toptal.calories.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class HikariLoader implements ApplicationRunner {

    private final HikariDataSource hikariDataSource;

    public HikariLoader(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @Autowired
    public void run(ApplicationArguments args) throws SQLException {
        hikariDataSource.getConnection();
    }
}
