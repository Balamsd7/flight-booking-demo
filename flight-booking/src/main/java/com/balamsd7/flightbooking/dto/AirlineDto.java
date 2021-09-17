package com.balamsd7.flightbooking.dto;

import lombok.Data;

@Data
public class AirlineDto {
    private  int airlineId;
    private String airlineNumber;
    private String airlineName;
    private long contactNumber;
    //private FlightDto flightDto;
}
