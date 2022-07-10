package com.toptal.calories.controllers;

import com.toptal.calories.services.UserAndAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserAndAuthService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") final long id) {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(userService.fetchSpecificUser(id), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping("/users/updateCaloriesLimit/{id}")
    public ResponseEntity<Object> updateUserCalorieLimit(@PathVariable(value = "id") final long id,
                                                         @RequestBody Map<String, String> params) {

        ResponseEntity<Object> response;
        try {
            response = new ResponseEntity<>(userService.updateCaloriesLimit(id,
                    Integer.parseInt(params.get("caloriesLimit"))), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping("/users/updateExpenseLimit/{id}")
    public ResponseEntity<Object> updateUserExpenseLimit(@PathVariable(value = "id") final long id,
                                                         @RequestBody Map<String, String> params) {

        ResponseEntity<Object> response;
        try {
            response = new ResponseEntity<>(userService.updateExpenseLimit(id,
                    Integer.parseInt(params.get("expenseLimit"))), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, String> params) {

        ResponseEntity<Object> response;
        try {
            response = new ResponseEntity<>(userService.addUser(params.get("name"), params.get("role")),
                    HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> removeUser(@PathVariable(value = "id") final long id) {

        ResponseEntity<Object> response;
        try {
            response = new ResponseEntity<>(userService.deleteUser(id),
                    HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
