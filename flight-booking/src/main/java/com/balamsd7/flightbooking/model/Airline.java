package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String airlineNumber;
    private String airlineName;
    private long contactNumber;

    /*@OneToOne
    private Flight flight;*/
}
