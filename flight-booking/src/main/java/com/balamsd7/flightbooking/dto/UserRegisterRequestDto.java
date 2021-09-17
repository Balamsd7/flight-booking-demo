package com.balamsd7.flightbooking.dto;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String emailId;
    private long mobileNumber;
    private String country;
    private String state;
    private String city;
}
