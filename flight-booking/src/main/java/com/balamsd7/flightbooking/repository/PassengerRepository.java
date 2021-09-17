package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
