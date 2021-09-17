package com.balamsd7.flightbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    //private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Autowired
    private RegisterService registerService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.balamsd7.flightbooking.model.User userDetails = registerService.getUserDetailsByUserName(username);

        if(userDetails!=null && userDetails.getUserName().equals(username)){
            return new User(username,userDetails.getPassword(),new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found : "+username);
        }
    }
}
