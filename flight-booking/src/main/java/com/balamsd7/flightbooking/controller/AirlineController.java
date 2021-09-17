package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.AirlineDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.AirlineService;
import com.balamsd7.flightbooking.utils.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1.0/flight/airline")
@RestController
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDataDto> createAirline(@RequestBody AirlineDto airlineDto){
        return APIResponseBuilder.buildResponseFromDto(airlineService.createAirline(airlineDto));
    }
    @GetMapping("/getAllAirline")
    public ResponseEntity<ResponseDataDto> getAllAirline (){
        return APIResponseBuilder.buildResponseFromDto(airlineService.getAllAirline());
    }

    @GetMapping("/getAirlineById")
    public ResponseEntity<ResponseDataDto> getAirlineById (@RequestParam("id") int airlineId){
        return APIResponseBuilder.buildResponseFromDto(airlineService.getAirlineById(airlineId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDataDto> deleteByAirlineId (@RequestParam("id") int airlineId){
        return APIResponseBuilder.buildResponseFromDto(airlineService.deleteByAirlineId(airlineId));
    }

}
