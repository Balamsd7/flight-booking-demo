package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.BookingDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.BookingService;
import com.balamsd7.flightbooking.utils.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1.0/flight/booking")
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    public ResponseEntity<ResponseDataDto> createBooking(@RequestBody BookingDto bookingDto){
        return APIResponseBuilder.buildResponseFromDto(bookingService.createBooking(bookingDto));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseDataDto> updateBooking(@RequestBody BookingDto bookingDto){
        return APIResponseBuilder.buildResponseFromDto(bookingService.updateBooking(bookingDto));
    }

    @GetMapping("/ticket/{pnr}")
    public ResponseEntity<ResponseDataDto> getBookedTicketByPnrNumber(@PathVariable("pnr") String pnrNumber){
        return APIResponseBuilder.buildResponseFromDto(bookingService.getBookedTicketByPnrNumber(pnrNumber));
    }

    @GetMapping("/history/{emailId}")
    public ResponseEntity<ResponseDataDto> getBookedTicketByEmailId(@PathVariable("emailId") String emailId){
        return APIResponseBuilder.buildResponseFromDto(bookingService.getBookedTicketByEmailId(emailId));
    }
    @DeleteMapping("/cancel/{pnr}")
    public ResponseEntity<ResponseDataDto> cancelTicketByPnrNumber(@PathVariable("pnr") String pnrNumber){
        return APIResponseBuilder.buildResponseFromDto(bookingService.cancelTicketByPnrNumber(pnrNumber));
    }
}
