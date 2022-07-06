package com.toptal.calories.dao.queries;

public class Queries {

    private Queries() {
        throw new IllegalStateException("Queries Class. Cannot instantiate!");
    }

    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String GET_SPECIFIC_USER = "SELECT * FROM users WHERE id = :id";
    public static final String UPDATE_CALORIES_LIMIT = "UPDATE users SET calories_limit = :caloriesLimit WHERE id = :id RETURNING *";
    public static final String UPDATE_EXPENSE_LIMIT = "UPDATE users SET expense_limit = :expenseLimit WHERE id = :id RETURNING *";
    public static final String INSERT_USER = "INSERT INTO users (name, role, calories_limit, expense_limit) VALUES (:name,:role,:caloriesLimit,:expenseLimit) RETURNING *";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = :id RETURNING *";
    public static final String GET_ALL_MEALS = "SELECT * FROM meals";
    public static final String GET_SPECIFIC_MEAL = "SELECT * FROM meals WHERE id = :id";
    public static final String GET_ALL_MEALS_FOR_USER_ON_DATE = "SELECT * FROM meals WHERE user_id = :userId AND DATE(date) = DATE(:date)";
    public static final String INSERT_MEAL = "INSERT INTO meals (user_id, meal_name, calories, price, date) VALUES (:userId,:mealName,:calories,:price,:date) RETURNING *";
    public static final String DELETE_MEAL = "DELETE FROM meals WHERE id = :id RETURNING *";
    public static final String DELETE_ALL_MEALS_FOR_USER = "DELETE FROM meals WHERE user_id = :userId RETURNING *";
}
