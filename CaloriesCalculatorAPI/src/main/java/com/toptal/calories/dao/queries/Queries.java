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
    public static final String GET_ALL_MEALS = "SELECT * FROM meals ORDER BY date DESC";
    public static final String GET_SPECIFIC_MEAL = "SELECT * FROM meals WHERE id = :id";
    public static final String GET_ALL_MEALS_FOR_USER_ON_DATE = "SELECT * FROM meals WHERE user_id = :userId AND DATE(date) = :date";
    public static final String GET_ALL_MEALS_FOR_USER = "SELECT * FROM meals WHERE user_id = :userId ORDER BY date DESC";
    public static final String GET_EARLIEST_DATE = "SELECT DATE(MIN(date)) FROM meals WHERE user_id = :userId";
    public static final String INSERT_MEAL = "INSERT INTO meals (user_id, meal_name, calories, price, date, user_name) VALUES (:userId,:mealName,:calories,:price,:date,:userName) RETURNING *";
    public static final String DELETE_MEAL = "DELETE FROM meals WHERE id = :id RETURNING *";
    public static final String DELETE_ALL_MEALS_FOR_USER = "DELETE FROM meals WHERE user_id = :userId RETURNING *";
    public static final String GET_AUTH_BY_TOKEN = "SELECT * FROM auth WHERE token = :token";
    public static final String GET_AUTH_BY_USER_ID = "SELECT * FROM auth WHERE user_id = :userId";
    public static final String UPDATE_TOKEN = "UPDATE auth SET token = :token, expiry = :expiry WHERE user_id = :userId RETURNING *";
    public static final String INSERT_TOKEN = "INSERT INTO auth (user_id, token, expiry) VALUES (:userId,:token,:expiry) RETURNING *";
}
