package com.balamsd7.flightbooking.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookingDto {
    private int bookingId;
    private String flightId;
    private String name;
    private String emailId;
    private int noOfSeats;
    private String meal;
    private String status;
    //private String seatNos;
    private String pnrNumber;
    private Set<PassengerDto> passengerDto;
}
