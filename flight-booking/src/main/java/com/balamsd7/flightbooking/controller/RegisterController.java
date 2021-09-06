package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.UserRegisterRequestDto;
import com.balamsd7.flightbooking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1.0/flight/airline")
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
           return ResponseEntity.ok( registerService.userRegister(userRegisterRequestDto));
    }
}
