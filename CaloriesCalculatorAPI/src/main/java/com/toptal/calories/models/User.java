package com.toptal.calories.models;

public class User {

    private Long id;
    private String role;
    private String name;
    private Integer caloriesLimit;
    private Integer expenseLimit;

    public User(Long id, String role, String name, Integer caloriesLimit, Integer expenseLimit) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.caloriesLimit = caloriesLimit;
        this.expenseLimit = expenseLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCaloriesLimit() {
        return caloriesLimit;
    }

    public void setCaloriesLimit(Integer caloriesLimit) {
        this.caloriesLimit = caloriesLimit;
    }

    public Integer getExpenseLimit() {
        return expenseLimit;
    }

    public void setExpenseLimit(Integer expenseLimit) {
        this.expenseLimit = expenseLimit;
    }
}
