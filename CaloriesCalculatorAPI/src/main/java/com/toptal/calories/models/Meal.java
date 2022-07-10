package com.toptal.calories.models;

public class Meal {

    private Long id;
    private Long userId;
    private String mealName;
    private Integer calories;
    private Integer price;
    private String date;

    public Meal(Long id, Long userId, String mealName, Integer calories, Integer price, String date) {
        this.id = id;
        this.userId = userId;
        this.mealName = mealName;
        this.calories = calories;
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
