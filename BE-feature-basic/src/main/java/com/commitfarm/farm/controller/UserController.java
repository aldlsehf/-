package com.commitfarm.farm.controller;

import com.commitfarm.farm.domain.Users;
import com.commitfarm.farm.dto.user.CreateUserReq;
import com.commitfarm.farm.dto.user.LoginReq;
import com.commitfarm.farm.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersService userService;

    //로그인 api
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReq loginReq) {
        try {
            userService.login(loginReq.getId(), loginReq.getPassword());
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }


    //회원가입 api
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody CreateUserReq createUserReq) {
        try {
            userService.createUser(createUserReq);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



}
