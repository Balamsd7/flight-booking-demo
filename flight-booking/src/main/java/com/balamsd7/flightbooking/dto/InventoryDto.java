package com.balamsd7.flightbooking.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class InventoryDto {
    private int inventoryId;
    private String airLine;
    private String flightId;
    private String fromPlace;
    private String toPlace;
    private Date startDateTime;
    private Date endDateTime;
    private String scheduledDays;
    private String instrument;
    private int businessClassSeats;
    private int nonBusinessClassSeats;
    private double ticketCost;
    private int numberOfRows;
    private String meal;
}
