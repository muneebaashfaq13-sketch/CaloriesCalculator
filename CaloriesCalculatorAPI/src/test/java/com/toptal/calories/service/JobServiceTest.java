package com.toptal.calories.service;

import com.toptal.calories.common.Constants;
import com.toptal.calories.dao.AuthDao;
import com.toptal.calories.dao.MealDao;
import com.toptal.calories.dao.UserDao;
import com.toptal.calories.exceptions.ExpireException;
import com.toptal.calories.exceptions.UserException;
import com.toptal.calories.models.AuthObject;
import com.toptal.calories.models.Meal;
import com.toptal.calories.models.ReportObject;
import com.toptal.calories.models.User;
import com.toptal.calories.services.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class JobServiceTest {

    @Autowired
    JobService jobService;

    @MockBean
    UserDao userDao;

    @MockBean
    AuthDao authDao;

    @MockBean
    MealDao mealDao;

    User user = new User(1L, "User", "Test", 2100, 1000);
    User updatedUser = new User(1L, "User", "Test", 100, 50);
    AuthObject auth = new AuthObject(1L, "XXXXXXXXXXXXXXXXXXXXXXX", new Timestamp(System.currentTimeMillis() + Constants.MINUTES_TO_ADD * 60000));

    Meal meal = new Meal(1L, "Test", 1l, "Test Meal", 100, 30, "2022-01-01");

    @Test
    void testFetchAllUsers() {
        PowerMockito.when(userDao.fetchAllUsers()).thenReturn(List.of(user));
        List<User> returnValue = jobService.fetchAllUsers();
        Assertions.assertEquals(1, returnValue.size());
    }

    @Test
    void testFetchAllUserId() {
        PowerMockito.when(userDao.fetchAllUsers()).thenReturn(List.of(user));
        List<Long> returnValue = jobService.fetchAllUserId();
        Assertions.assertEquals(1L, returnValue.get(0));
    }

    @Test
    void testFetchSpecificUser() {
        PowerMockito.when(userDao.fetchSpecificUser(user.getId())).thenReturn(user);
        PowerMockito.when(authDao.getAuth(1L)).thenReturn(auth);
        Map<String, Object> returnValue = jobService.fetchSpecificUser(1L);
        Assertions.assertEquals(2, returnValue.size());
    }

    @Test
    void testUpdateCaloriesLimit() {
        PowerMockito.when(userDao.updateCaloriesLimit(user.getId(), 100)).thenReturn(updatedUser);
        User returnValue = jobService.updateCaloriesLimit(user.getId(), 100);
        Assertions.assertEquals(100, returnValue.getCaloriesLimit());
    }

    @Test
    void testUpdateExpenseLimit() {
        PowerMockito.when(userDao.updateExpenseLimit(user.getId(), 50)).thenReturn(updatedUser);
        User returnValue = jobService.updateExpenseLimit(user.getId(), 50);
        Assertions.assertEquals(50, returnValue.getExpenseLimit());
    }

    @Test
    void testAddUser() {
        PowerMockito.when(userDao.addUser(user.getName(), user.getRole())).thenReturn(user);
        PowerMockito.when(authDao.addToken(anyLong(), anyString(), any(Timestamp.class))).thenReturn(auth);
        Map<String, Object> returnValue = jobService.addUser(user.getName(), "User");
        Assertions.assertEquals(2, returnValue.size());
    }

    @Test
    void testDeleteUser() {
        PowerMockito.when(userDao.deleteUser(user.getId())).thenReturn(user);
        User returnValue = jobService.deleteUser(user.getId());
        Assertions.assertEquals(1l, returnValue.getId());
    }

    @Test
    void testGetAuth() throws ExpireException, UserException {
        PowerMockito.when(authDao.getAuth(auth.getToken())).thenReturn(auth);
        PowerMockito.when(userDao.fetchSpecificUser(user.getId())).thenReturn(user);
        User returnValue = jobService.getAuth(auth.getToken());
        Assertions.assertEquals(1l, returnValue.getId());
    }

    @Test
    void testUpdateToken() throws UserException {
        PowerMockito.when(authDao.getAuth(user.getId())).thenReturn(auth);
        PowerMockito.when(authDao.updateToken(anyLong(), anyString(), any(Timestamp.class))).thenReturn(auth);
        AuthObject returnValue = jobService.updateToken(user.getId());
        Assertions.assertEquals(auth.getExpiry(), returnValue.getExpiry());
    }

    @Test
    void testFetchAllMeals() {
        PowerMockito.when(mealDao.fetchAllMeals()).thenReturn(List.of(meal));
        List<Meal> returnValue = jobService.fetchAllMeals();
        Assertions.assertEquals(1, returnValue.size());
    }

    @Test
    void testFetchSpecificMeal() {
        PowerMockito.when(mealDao.fetchSpecificMeal(meal.getId())).thenReturn(meal);
        Meal returnValue = jobService.fetchSpecificMeal(meal.getId());
        Assertions.assertEquals(1L, returnValue.getId());
    }

    @Test
    void testFetchAllMealsByDate() {
        PowerMockito.when(mealDao.fetchAllMeals()).thenReturn(List.of(meal));
        Map<String, List<Meal>> returnValue = jobService.fetchAllMealsByDate();
        Assertions.assertEquals(1, returnValue.get("2022-01-01").size());
    }

    @Test
    public void testFetchAllMealsForUser() {
        PowerMockito.when(mealDao.fetchAllMealsForUser(user.getId())).thenReturn(List.of(meal));
        Map<String, List<Meal>> returnValue = jobService.fetchAllMealsForUser(user.getId());
        Assertions.assertEquals(1, returnValue.get("2022-01-01").size());
    }

    @Test
    void testFetchEarliestMealDate() {
        PowerMockito.when(mealDao.fetchEarliestMealDate(user.getId())).thenReturn("2022-01-01");
        String returnValue = jobService.fetchEarliestMealDate(user.getId());
        Assertions.assertEquals("2022-01-01", returnValue);
    }

    @Test
    void testAddMeal() {
        Date date = Date.valueOf(meal.getDate());
        PowerMockito.when(userDao.fetchSpecificUser(user.getId())).thenReturn(user);
        PowerMockito.when(mealDao.addMeal(user.getId(), user.getName(), meal.getMealName(), meal.getCalories(), meal.getPrice(), date)).thenReturn(meal);
        Meal returnValue = jobService.addMeal(user.getId(), meal.getMealName(), meal.getCalories(), meal.getPrice(), date);
        Assertions.assertEquals(meal.getId(), returnValue.getId());
    }

    @Test
    void testUpdateMeal() {
        Date date = Date.valueOf(meal.getDate());
        PowerMockito.when(mealDao.updateMeal(meal.getId(), meal.getMealName(), meal.getCalories(), meal.getPrice(), date)).thenReturn(meal);
        Meal returnValue = jobService.updateMeal(meal.getId(), meal.getMealName(), meal.getCalories(), meal.getPrice(), date);
        Assertions.assertEquals(meal.getId(), returnValue.getId());
    }

    @Test
    void testDeleteMeal() {
        PowerMockito.when(mealDao.deleteMeal(meal.getId())).thenReturn(meal);
        Meal returnValue = jobService.deleteMeal(meal.getId());
        Assertions.assertEquals(meal.getId(), returnValue.getId());
    }

    @Test
    void testDeleteAllMealsForUser() {
        PowerMockito.when(mealDao.deleteAllMealsForUser(user.getId())).thenReturn(List.of(meal));
        List<Meal> returnValue = jobService.deleteAllMealsForUser(user.getId());
        Assertions.assertEquals(1, returnValue.size());
    }

    @Test
    void testGetReport() {
        PowerMockito.when(mealDao.fetchAllMeals()).thenReturn(List.of(meal));
        List<ReportObject> returnValue = jobService.getReport();
        Assertions.assertEquals(2, returnValue.size());
    }
}
