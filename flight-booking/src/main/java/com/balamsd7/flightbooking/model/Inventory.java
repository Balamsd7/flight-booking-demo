package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String airLine;
    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private Date startDateTime;
    private Date endDateTime;
    private String scheduledDays;
    private String instrument;
    private int totalBusinessClassSeats;
    private int totalNonBusinessClassSeats;
    private double ticketCost;
    private int numberOfRows;
    private String meal;
}
