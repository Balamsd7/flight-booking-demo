package com.balamsd7.flightbooking.dto;

import java.io.Serializable;

public class LoginResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private int roleId;

    public LoginResponseDto(){}

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
