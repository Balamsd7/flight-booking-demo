package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.dto.UserRegisterRequestDto;
import com.balamsd7.flightbooking.model.User;
import com.balamsd7.flightbooking.repository.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegisterService {

    @Autowired
    private UserRegisterRepository userRegisterRepository;

    public  String userRegister(UserRegisterRequestDto userRegisterRequestDto) {

        String resultMsg = "";
        User user = toRegisterEntity(userRegisterRequestDto);

        User savedUser  =  userRegisterRepository.save(user);

        if(Objects.nonNull(savedUser)){
            resultMsg = "S";
        }else{
            resultMsg = "F";
        }

        return resultMsg;
    }

    private User toRegisterEntity(UserRegisterRequestDto userRegisterRequestDto) {
        User user = new User();
        user.setUserName(userRegisterRequestDto.getUserName());
        user.setPassword(userRegisterRequestDto.getPassword());
        user.setFirstName(userRegisterRequestDto.getFirstName());
        user.setLastName(userRegisterRequestDto.getLastName());
        user.setEmailId(userRegisterRequestDto.getEmailId());
        user.setMobileNumber(userRegisterRequestDto.getMobileNumber());
        user.setCountry(userRegisterRequestDto.getCountry());
        user.setState(userRegisterRequestDto.getState());
        user.setCity(userRegisterRequestDto.getCity());

        return  user;
    }
}
