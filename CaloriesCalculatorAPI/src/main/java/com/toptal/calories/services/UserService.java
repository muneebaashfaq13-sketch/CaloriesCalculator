package com.toptal.calories.services;

import com.toptal.calories.dao.UserDao;
import com.toptal.calories.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> fetchAllUsers() {
        return userDao.fetchAllUsers();
    }

    public User fetchSpecificUser(long id) {
        return userDao.fetchSpecificUser(id);
    }

    public User updateCaloriesLimit(long id, int caloriesLimit) {
        return userDao.updateCaloriesLimit(id, caloriesLimit);
    }

    public User updateExpenseLimit(long id, int expenseLimit) {
        return userDao.updateExpenseLimit(id, expenseLimit);
    }

    public User addUser(String name, String role) {
        return userDao.addUser(name, role);
    }

    public User deleteUser(long id) {
        return userDao.deleteUser(id);
    }

}
