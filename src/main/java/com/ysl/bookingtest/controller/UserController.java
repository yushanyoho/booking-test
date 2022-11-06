package com.ysl.bookingtest.controller;

import com.ysl.bookingtest.model.CreateUserRequest;
import com.ysl.bookingtest.model.Student;
import com.ysl.bookingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/createStudentAccount")
    public Student create(@RequestBody CreateUserRequest request) {

        return userService.createStudentAccount(request.getUsername());
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestBody Student student) {
        userService.createStudentAccount(student);
        return "Added one student!";
    }
}
