package com.toptal.calories.controllers;

import com.toptal.calories.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReportController {

    @Autowired
    private JobService reportService;

    @GetMapping("/report")
    public ResponseEntity<Object> getReport() {

        ResponseEntity<Object> response;

        try {
            response = new ResponseEntity<>(reportService.getReport(), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
