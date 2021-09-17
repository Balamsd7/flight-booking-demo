package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

     @Query("SELECT b from Booking b WHERE b.pnrNumber = ?1")
     Booking findByPnrNumber(String pnrNumber);

     List<Booking> findByEmailId(String emailId);
}
