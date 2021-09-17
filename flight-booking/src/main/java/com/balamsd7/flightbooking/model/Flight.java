package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int airlineId;
    private String flightNumber;
    private String flightName;
    private String flightAddress;

    private int capacity;


    @OneToOne(cascade=CascadeType.ALL)
    private Instrument instrument;
}
