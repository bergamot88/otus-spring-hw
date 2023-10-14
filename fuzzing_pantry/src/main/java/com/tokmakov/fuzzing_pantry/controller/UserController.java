package com.tokmakov.fuzzing_pantry.controller;

import com.tokmakov.fuzzing_pantry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> saveNewUser(@RequestParam(value = "username") String userName,
                                              @RequestParam(value = "password") String password) {
        userService.saveNew(userName, password);
        return ResponseEntity.status(HttpStatus.CREATED).body("User was saved");
    }
}
