package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String flightId;
    private String name;
    private String emailId;
    private int noOfSeats;
    private String meal;
    //private String seatNos;
    private String status;
    private String pnrNumber = "PNR"+ UUID.randomUUID().toString();

    @OneToMany(mappedBy = "booking")
    private Set<Passenger> passengers;


}
