package com.balamsd7.flightbooking.Exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String errorMsg){
        super(errorMsg);
    }
}
