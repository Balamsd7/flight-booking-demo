package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.dto.UserRegisterRequestDto;
import com.balamsd7.flightbooking.service.RegisterService;
import com.balamsd7.flightbooking.utils.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1.0/flight/airline")
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDataDto> userRegister(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
           return APIResponseBuilder.buildResponseFromDto(registerService.userRegister(userRegisterRequestDto));
    }
}
