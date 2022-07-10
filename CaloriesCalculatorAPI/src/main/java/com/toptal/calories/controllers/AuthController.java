package com.toptal.calories.controllers;

import com.toptal.calories.exceptions.ExpireException;
import com.toptal.calories.exceptions.UserException;
import com.toptal.calories.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private JobService authService;

    @GetMapping("/auth/{token}")
    public ResponseEntity<Object> authenticate(@PathVariable(value = "token") final String token) {
        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(authService.getAuth(token), HttpStatus.OK);
        } catch (UserException ue) {
            response = new ResponseEntity<>(ue.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (ExpireException ee) {
            response = new ResponseEntity<>(ee.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/auth/getToken/{userId}")
    public ResponseEntity<Object> getToken(@PathVariable(value = "userId") final long userId) {
        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(authService.getAuth(userId), HttpStatus.OK);
        } catch (UserException ue) {
            response = new ResponseEntity<>(ue.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping("/auth/updateToken/{userId}")
    public ResponseEntity<Object> updateToken(@PathVariable(value = "userId") final long userId) {
        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(authService.updateToken(userId), HttpStatus.OK);
        } catch (UserException ue) {
            response = new ResponseEntity<>(ue.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
