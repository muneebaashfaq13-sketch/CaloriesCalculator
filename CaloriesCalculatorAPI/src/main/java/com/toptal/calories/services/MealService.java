package com.toptal.calories.services;

import com.toptal.calories.dao.MealDao;
import com.toptal.calories.models.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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

    public HashMap<String, List<Meal>> fetchAllMealsForUser(long userId) {
        HashMap<String, List<Meal>> mealsMap = new HashMap<>();
        List<Meal> meals = mealDao.fetchAllMealsForUser(userId);

        for (Meal meal : meals) {
            List<Meal> temp = mealsMap.get(meal.getDate());
            if (temp == null) {
                temp = new ArrayList<>();
            }
            temp.add(meal);
            mealsMap.put(meal.getDate(), temp);
        }

        return mealsMap;
    }

    public String fetchEarliestMealDate(long userId) {
        return mealDao.fetchEarliestMealDate(userId);
    }

    public Meal addMeal(long userId, String mealName, int calories, int price, Date date) {
        return mealDao.addMeal(userId, mealName, calories, price, date);
    }

    public Meal deleteMeal(long id) {
        return mealDao.deleteMeal(id);
    }

    public List<Meal> deleteAllMealsForUser(long userId) {
        return mealDao.deleteAllMealsForUser(userId);
    }
}
