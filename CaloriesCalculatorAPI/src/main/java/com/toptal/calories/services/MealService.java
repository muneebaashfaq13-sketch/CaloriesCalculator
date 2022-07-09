package com.toptal.calories.services;

import com.toptal.calories.dao.MealDao;
import com.toptal.calories.models.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealDao mealDao;

    public List<Meal> fetchAllMeals() {
        return mealDao.fetchAllMeals();
    }

    public Meal fetchSpecificMeal(long id) {
        return mealDao.fetchSpecificMeal(id);
    }

    public List<Meal> fetchAllMealsForUserOnDate(long userId, Date date) {
        return mealDao.fetchAllMealsForUserOnDate(userId, date);
    }

    public String fetchEarliestMealDate(long userId) {
        return mealDao.fetchEarliestMealDate(userId);
    }

    public Meal addMeal(long userId, String mealName, int calories, int price, Timestamp date) {
        return mealDao.addMeal(userId, mealName, calories, price, date);
    }

    public Meal deleteMeal(long id) {
        return mealDao.deleteMeal(id);
    }

    public List<Meal> deleteAllMealsForUser(long userId) {
        return mealDao.deleteAllMealsForUser(userId);
    }
}
