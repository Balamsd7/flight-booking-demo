package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    Boolean existsByAirlineName(String airlineName);
}
