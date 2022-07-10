package com.toptal.calories.mappers;

import com.toptal.calories.models.Meal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealMapper implements RowMapper<Meal> {

    @Override
    public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Meal(
                rs.getLong("id"),
                rs.getString("user_name"),
                rs.getLong("user_id"),
                rs.getString("meal_name"),
                rs.getInt("calories"),
                rs.getInt("price"),
                rs.getString("date")
        );
    }
}