package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.FlightDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.FlightService;
import com.balamsd7.flightbooking.utils.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1.0/flight/details")
@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDataDto> createFlight(@RequestBody FlightDto flightDto){
        return APIResponseBuilder.buildResponseFromDto(flightService.createFlight(flightDto));
    }
    @GetMapping("/getAllFlight")
    public ResponseEntity<ResponseDataDto> getAllFlight (){
        return APIResponseBuilder.buildResponseFromDto(flightService.getAllFlight());
    }

    @GetMapping("/getFlightById")
    public ResponseEntity<ResponseDataDto> getFlightById (@RequestParam("id") int flightId){
        return APIResponseBuilder.buildResponseFromDto(flightService.getFlightById(flightId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDataDto> deleteByFlightId (@RequestParam("id") int flightId){
        return APIResponseBuilder.buildResponseFromDto(flightService.deleteByFlightId(flightId));
    }
}
