package com.toptal.calories.controllers;

import com.toptal.calories.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@RestController
@CrossOrigin
public class MealController {

    @Autowired
    private JobService mealService;

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

    @GetMapping("/meals/userMealsByDate/{userId}")
    public ResponseEntity<Object> getAllMealsForUserOnDate(@PathVariable(value = "userId") final long userId,
                                                           @RequestParam(required = true, defaultValue = "0000-00-00") Date date) {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(mealService.fetchAllMealsForUserOnDate(userId, date), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/meals/allMealsForUser/{userId}")
    public ResponseEntity<Object> getAllMealsForUser(@PathVariable(value = "userId") final long userId) {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(mealService.fetchAllMealsForUser(userId), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/meals/earliestMealDate/{userId}")
    public ResponseEntity<Object> getEarliestMealDate(@PathVariable(value = "userId") final long userId) {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(mealService.fetchEarliestMealDate(userId), HttpStatus.OK);
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
                    Date.valueOf(params.get("date"))),
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
