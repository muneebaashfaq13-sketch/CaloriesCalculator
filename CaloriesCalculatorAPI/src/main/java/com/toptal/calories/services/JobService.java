package com.toptal.calories.services;

import com.toptal.calories.common.Constants;
import com.toptal.calories.common.Utils;
import com.toptal.calories.dao.AuthDao;
import com.toptal.calories.dao.MealDao;
import com.toptal.calories.dao.UserDao;
import com.toptal.calories.exceptions.ExpireException;
import com.toptal.calories.exceptions.UserException;
import com.toptal.calories.models.AuthObject;
import com.toptal.calories.models.Meal;
import com.toptal.calories.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthDao authDao;

    @Autowired
    private MealDao mealDao;

    public List<User> fetchAllUsers() {
        return userDao.fetchAllUsers();
    }

    public Map<String, Object> fetchSpecificUser(long id) {
        HashMap<String, Object> userInfo = new HashMap<>();
        User user = userDao.fetchSpecificUser(id);
        AuthObject auth = authDao.getAuth(user.getId());
        userInfo.put("user", user);
        userInfo.put("auth", auth);
        return userInfo;
    }

    public User updateCaloriesLimit(long id, int caloriesLimit) {
        return userDao.updateCaloriesLimit(id, caloriesLimit);
    }

    public User updateExpenseLimit(long id, int expenseLimit) {
        return userDao.updateExpenseLimit(id, expenseLimit);
    }

    public Map<String, Object> addUser(String name, String role) {
        HashMap<String, Object> userInfo = new HashMap<>();
        User user = userDao.addUser(name, role);
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis() + Constants.MINUTES_TO_ADD * 60000);
        AuthObject auth = authDao.addToken(user.getId(), Utils.generateNewToken(user.getId()), newTimestamp);
        userInfo.put("user", user);
        userInfo.put("auth", auth);
        return userInfo;
    }

    public User deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    public User getAuth(String token) throws ExpireException, UserException {

        long userId = -1L;
        try {
            AuthObject authObject = authDao.getAuth(token);
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            if (authObject.getExpiry().before(currentTimestamp))
                throw new ExpireException("Token Expired.");
            userId = authObject.getUserId();
        } catch (EmptyResultDataAccessException erdae) {
            throw new UserException("No Such User.");
        } catch (Exception e) {
            throw e;
        }

        try {
            return userDao.fetchSpecificUser(userId);
        } catch (EmptyResultDataAccessException erdae) {
            throw new UserException("No Such User.");
        } catch (Exception e) {
            throw e;
        }
    }

    public AuthObject getAuth(long userId) throws UserException {
        try {
            return authDao.getAuth(userId);
        } catch (EmptyResultDataAccessException erdae) {
            throw new UserException("No Such User.");
        } catch (Exception e) {
            throw e;
        }
    }

    public AuthObject updateToken(long userId) throws UserException {
        try {
            AuthObject authObject = authDao.getAuth(userId);
            Timestamp newTimestamp = new Timestamp(System.currentTimeMillis() + Constants.MINUTES_TO_ADD * 60000);
            return authDao.updateToken(authObject.getUserId(),
                    Utils.generateNewToken(authObject.getUserId()),
                    newTimestamp);
        } catch (EmptyResultDataAccessException erdae) {
            throw new UserException("No Such User.");
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Meal> fetchAllMeals() {
        return mealDao.fetchAllMeals();
    }

    public Meal fetchSpecificMeal(long id) {
        return mealDao.fetchSpecificMeal(id);
    }

    public Map<String, List<Meal>> fetchAllMealsByDate() {
        HashMap<String,List<Meal>> mealsMap = new HashMap<>();
        List<Meal> meals = mealDao.fetchAllMeals();

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

    public Map<String, List<Meal>> fetchAllMealsForUser(long userId) {
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
        User user = userDao.fetchSpecificUser(userId);
        return mealDao.addMeal(userId, user.getName(), mealName, calories, price, date);
    }

    public Meal updateMeal(long id, String mealName, int calories, int price, Date date) {
        return mealDao.updateMeal(id, mealName, calories, price, date);
    }

    public Meal deleteMeal(long id) {
        return mealDao.deleteMeal(id);
    }

    public List<Meal> deleteAllMealsForUser(long userId) {
        return mealDao.deleteAllMealsForUser(userId);
    }

}
