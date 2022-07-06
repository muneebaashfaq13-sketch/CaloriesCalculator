package com.toptal.calories.dao;

import com.toptal.calories.common.Constants;
import com.toptal.calories.dao.queries.Queries;
import com.toptal.calories.mappers.UserMapper;
import com.toptal.calories.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> fetchAllUsers() {
        return namedParameterJdbcTemplate.query(Queries.GET_ALL_USERS, new MapSqlParameterSource(), new UserMapper());
    }

    public User fetchSpecificUser(long id) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.ID, id);
        return namedParameterJdbcTemplate.queryForObject(Queries.GET_SPECIFIC_USER, inQueryParams, new UserMapper());
    }

    public User updateCaloriesLimit(long id, int caloriesLimit) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.ID, id);
        inQueryParams.addValue(Constants.CALORIES_LIMIT_LITERAL, caloriesLimit);
        return namedParameterJdbcTemplate.queryForObject(Queries.UPDATE_CALORIES_LIMIT, inQueryParams, new UserMapper());
    }

    public User updateExpenseLimit(long id, int expenseLimit) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.ID, id);
        inQueryParams.addValue(Constants.EXPENSE_LIMIT_LITERAL, expenseLimit);
        return namedParameterJdbcTemplate.queryForObject(Queries.UPDATE_EXPENSE_LIMIT, inQueryParams, new UserMapper());
    }

    public User addUser(String name, String role) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.NAME, name);
        inQueryParams.addValue(Constants.ROLE, role);
        inQueryParams.addValue(Constants.CALORIES_LIMIT_LITERAL, Constants.CALORIES_LIMIT);
        inQueryParams.addValue(Constants.EXPENSE_LIMIT_LITERAL, Constants.EXPENSE_LIMIT);
        return namedParameterJdbcTemplate.queryForObject(Queries.INSERT_USER, inQueryParams, new UserMapper());
    }

    public User deleteUser(long id) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.ID, id);
        return namedParameterJdbcTemplate.queryForObject(Queries.DELETE_USER, inQueryParams, new UserMapper());
    }
}
