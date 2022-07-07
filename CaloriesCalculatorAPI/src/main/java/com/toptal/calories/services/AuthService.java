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

@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private UserDao userDao;

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
