package com.balamsd7.flightbooking.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class FlightDto {
    private int flightId;
    private int capacity;
    private int airlineId;
    private String flightNumber;
    private String flightName;
    private String flightAddress;
    private  InstrumentDto instrumentDto;

}
