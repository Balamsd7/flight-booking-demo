package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private int roleId;
    private String firstName;
    private String lastName;
    private String emailId;
    private long mobileNumber;
    private String country;
    private String state;
    private String city;
}
