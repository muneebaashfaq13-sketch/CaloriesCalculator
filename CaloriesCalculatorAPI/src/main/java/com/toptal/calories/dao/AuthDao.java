package com.toptal.calories.dao;

import com.toptal.calories.common.Constants;
import com.toptal.calories.dao.queries.Queries;
import com.toptal.calories.mappers.AuthObjectMapper;
import com.toptal.calories.models.AuthObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class AuthDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthObject getAuth(String token) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.TOKEN, token);
        return namedParameterJdbcTemplate.queryForObject(Queries.GET_AUTH_BY_TOKEN, inQueryParams, new AuthObjectMapper());
    }

    public AuthObject getAuth(long userId) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.USER_ID, userId);
        return namedParameterJdbcTemplate.queryForObject(Queries.GET_AUTH_BY_USER_ID, inQueryParams, new AuthObjectMapper());
    }

    public AuthObject updateToken(long userId, String token, Timestamp expiry) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.USER_ID, userId);
        inQueryParams.addValue(Constants.TOKEN, token);
        inQueryParams.addValue(Constants.EXPIRY, expiry);
        return namedParameterJdbcTemplate.queryForObject(Queries.UPDATE_TOKEN, inQueryParams, new AuthObjectMapper());
    }


}
