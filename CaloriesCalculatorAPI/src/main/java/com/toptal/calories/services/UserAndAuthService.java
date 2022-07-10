package com.toptal.calories.services;

import com.toptal.calories.common.Constants;
import com.toptal.calories.common.Utils;
import com.toptal.calories.dao.AuthDao;
import com.toptal.calories.dao.UserDao;
import com.toptal.calories.exceptions.ExpireException;
import com.toptal.calories.exceptions.UserException;
import com.toptal.calories.models.AuthObject;
import com.toptal.calories.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class UserAndAuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthDao authDao;

    public List<User> fetchAllUsers() {
        return userDao.fetchAllUsers();
    }

    public HashMap<String, Object> fetchSpecificUser(long id) {
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

    public HashMap<String, Object> addUser(String name, String role) {
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

}
