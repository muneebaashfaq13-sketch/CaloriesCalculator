package com.toptal.calories.mappers;

import com.toptal.calories.models.AuthObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthObjectMapper implements RowMapper<AuthObject> {

    @Override
    public AuthObject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AuthObject(
                rs.getLong("user_id"),
                rs.getString("token"),
                rs.getTimestamp("expiry")
        );
    }
}