package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    //List<Inventory> findByStartDateFromToPlace(Date startDateTime, String fromPlace, String toPlace);
    @Query("SELECT i from Inventory i WHERE i.startDateTime = ?1 AND i.fromPlace = ?2 AND i.toPlace = ?3")
     List<Inventory> findByStartDateFromToPlace(Date startDateTime, String fromPlace, String toPlace);
}
