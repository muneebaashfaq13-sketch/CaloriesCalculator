package com.toptal.calories.models;

public class ReportObject {
    private Integer userCount;
    private Float caloriesAverage;

    public ReportObject(Integer userCount, Float caloriesAverage) {
        this.userCount = userCount;
        this.caloriesAverage = caloriesAverage;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Float getCaloriesAverage() {
        return caloriesAverage;
    }

    public void setCaloriesAverage(Float caloriesAverage) {
        this.caloriesAverage = caloriesAverage;
    }
}
