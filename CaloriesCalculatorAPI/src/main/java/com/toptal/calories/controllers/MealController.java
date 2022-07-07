package com.toptal.calories.controllers;

import com.toptal.calories.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@CrossOrigin
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("/meals")
    public ResponseEntity<Object> getAllMeals() {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(mealService.fetchAllMeals(), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/meals/{id}")
    public ResponseEntity<Object> getMeal(@PathVariable(value = "id") final long id) {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(mealService.fetchSpecificMeal(id), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/meals/userMeals/{userId}")
    public ResponseEntity<Object> getAllMealsForUserOnDate(@PathVariable(value = "userId") final long userId,
                                                           @RequestParam(required = false, defaultValue = "2022-07-06 00:00:00") Timestamp date) {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(mealService.fetchAllMealsForUserOnDate(userId, date), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping("/meals/{userId}")
    public ResponseEntity<Object> addMeal(@PathVariable(value = "userId") final long userId,
                                          @RequestBody Map<String, String> params) {

        ResponseEntity<Object> response;
        try {
            response = new ResponseEntity<>(mealService.addMeal(userId,
                    params.get("mealName"),
                    Integer.parseInt(params.get("calories")),
                    Integer.parseInt(params.get("price")),
                    Timestamp.valueOf(params.get("date"))),
                    HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @DeleteMapping("/meals/{id}")
    public ResponseEntity<Object> removeMeal(@PathVariable(value = "id") final long id) {

        ResponseEntity<Object> response;
        try {
            response = new ResponseEntity<>(mealService.deleteMeal(id),
                    HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @DeleteMapping("/meals/userMeals/{userId}")
    public ResponseEntity<Object> removeAllMealsForUser(@PathVariable(value = "userId") final long userId) {

        ResponseEntity<Object> response;
        try {
            response = new ResponseEntity<>(mealService.deleteAllMealsForUser(userId),
                    HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
