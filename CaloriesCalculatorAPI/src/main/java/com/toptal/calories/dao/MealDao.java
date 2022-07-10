package com.toptal.calories.dao;

import com.toptal.calories.common.Constants;
import com.toptal.calories.dao.queries.Queries;
import com.toptal.calories.mappers.MealMapper;
import com.toptal.calories.models.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class MealDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Meal> fetchAllMeals() {
        return namedParameterJdbcTemplate.query(Queries.GET_ALL_MEALS, new MapSqlParameterSource(), new MealMapper());
    }

    public Meal fetchSpecificMeal(long id) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.ID, id);
        return namedParameterJdbcTemplate.queryForObject(Queries.GET_SPECIFIC_MEAL, inQueryParams, new MealMapper());
    }

    public List<Meal> fetchAllMealsForUserOnDate(long userId, Date date) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.USER_ID, userId);
        inQueryParams.addValue(Constants.DATE, date);
        return namedParameterJdbcTemplate.query(Queries.GET_ALL_MEALS_FOR_USER_ON_DATE, inQueryParams, new MealMapper());
    }

    public List<Meal> fetchAllMealsForUser(long userId) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.USER_ID, userId);
        return namedParameterJdbcTemplate.query(Queries.GET_ALL_MEALS_FOR_USER, inQueryParams, new MealMapper());
    }

    public String fetchEarliestMealDate(long userId) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.USER_ID, userId);
        return namedParameterJdbcTemplate.queryForObject(Queries.GET_EARLIEST_DATE, inQueryParams, String.class);
    }

    public Meal addMeal(long userId, String mealName, int calories, int price, Date date) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.USER_ID, userId);
        inQueryParams.addValue(Constants.MEAL_NAME, mealName);
        inQueryParams.addValue(Constants.CALORIES, calories);
        inQueryParams.addValue(Constants.PRICE, price);
        inQueryParams.addValue(Constants.DATE, date);
        return namedParameterJdbcTemplate.queryForObject(Queries.INSERT_MEAL, inQueryParams, new MealMapper());
    }

    public Meal deleteMeal(long id) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.ID, id);
        return namedParameterJdbcTemplate.queryForObject(Queries.DELETE_MEAL, inQueryParams, new MealMapper());
    }

    public List<Meal> deleteAllMealsForUser(long userId) {
        MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
        inQueryParams.addValue(Constants.USER_ID, userId);
        return namedParameterJdbcTemplate.query(Queries.DELETE_ALL_MEALS_FOR_USER, inQueryParams, new MealMapper());
    }
}
