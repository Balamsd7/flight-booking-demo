package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.config.JwtTokenUtil;
import com.balamsd7.flightbooking.dto.LoginRequestDto;
import com.balamsd7.flightbooking.dto.LoginResponseDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.User;
import com.balamsd7.flightbooking.service.JwtUserDetailsService;
import com.balamsd7.flightbooking.service.RegisterService;
import com.balamsd7.flightbooking.utils.APIResponseBuilder;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class JwtAuthController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDataDto> authenticateUser(@RequestBody LoginRequestDto request) throws Exception{
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {
            authenticate(request.getUserName(), request.getPassword());
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUserName());
            final String token = jwtTokenUtil.generateToken(userDetails);
            logger.info("Generated Token : " + token);
            User user = registerService.getUserDetailsByUserName(userDetails.getUsername());
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(token);
            loginResponseDto.setRoleId(user.getRoleId());
            responseDataDto.setMessage(CommonConstants.SUCCESS);
            responseDataDto.setResult(loginResponseDto);
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
            responseDataDto.setResult(null);
        }
        return APIResponseBuilder.buildResponseFromDto(responseDataDto);
    }

    private void authenticate(String username,String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException ex){
            throw new Exception("USER DISABLED ",ex);
        }catch (BadCredentialsException ex){
            throw new Exception("INVALID CREDENTIALS",ex);
        }
    }
}
