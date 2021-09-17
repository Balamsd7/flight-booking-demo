package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.Exception.BusinessException;
import com.balamsd7.flightbooking.dto.BookingDto;
import com.balamsd7.flightbooking.dto.PassengerDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.Booking;
import com.balamsd7.flightbooking.model.Passenger;
import com.balamsd7.flightbooking.repository.BookingRepository;
import com.balamsd7.flightbooking.repository.PassengerRepository;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public ResponseDataDto createBooking(BookingDto bookingDto) {
        String generatedPNRNumber = "";

        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {
            Booking booking = toBookingEntity(bookingDto);

            Booking savedBooking = bookingRepository.save(booking);

            StreamSupport
                    .stream(bookingDto.getPassengerDto().spliterator(), false)
                    .map(passengerDto -> toPassengerEntity(passengerDto))
                    .forEach(passenger -> {
                        // persist customer addresses
                        passenger.setBooking(savedBooking);
                        passengerRepository.save(passenger);
                    });
            if (Objects.nonNull(savedBooking)) {
                generatedPNRNumber = savedBooking.getPnrNumber();
                logger.info("Generated PNR Number : " + generatedPNRNumber);

                responseDataDto.setMessage(CommonConstants.SUCCESS);

            } else {
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        responseDataDto.setResult(generatedPNRNumber);
        return responseDataDto;

    }

    public ResponseDataDto updateBooking(BookingDto bookingDto) {

        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {

            Optional<Booking> optional = bookingRepository.findById(bookingDto.getBookingId());

            if (optional.isPresent()) {
                Booking existingBooking = optional.get();
                existingBooking.setName(bookingDto.getName());
                existingBooking.setEmailId(bookingDto.getEmailId());
                existingBooking.setNoOfSeats(bookingDto.getNoOfSeats());
                existingBooking.setMeal(bookingDto.getMeal());
                existingBooking.setStatus("Booked");
                //existingBooking.setSeatNos(bookingDto.getSeatNos());
                existingBooking.setPnrNumber(bookingDto.getPnrNumber());
                existingBooking.setPassengers(new LinkedHashSet<>());

                bookingDto.getPassengerDto().forEach(passengerDto -> {
                    Passenger passenger = new Passenger();
                    passenger.setName(passengerDto.getName());
                    passenger.setGender(passengerDto.getGender());
                    passenger.setAge(passengerDto.getAge());

                    existingBooking.getPassengers().add(passenger);
                });

                Booking savedBooking = bookingRepository.save(existingBooking);

                StreamSupport
                        .stream(bookingDto.getPassengerDto().spliterator(), false)
                        .map(passengerDto -> toPassengerEntity(passengerDto))
                        .forEach(passenger -> {
                            // persist customer addresses
                            passenger.setBooking(savedBooking);
                            passengerRepository.save(passenger);
                        });
                if (Objects.nonNull(savedBooking)) {

                    responseDataDto.setMessage(CommonConstants.SUCCESS);

                } else {
                    responseDataDto.setMessage(CommonConstants.NDF);
                }
            } else {
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }

    public ResponseDataDto  getBookedTicketByPnrNumber(String pnrNumber) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {
            Booking booking = bookingRepository.findByPnrNumber(pnrNumber);

            if (Objects.nonNull(booking)) {
                BookingDto bookingDto = toBookingDto(booking);
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(bookingDto);
            } else {
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;

    }

    public  ResponseDataDto getBookedTicketByEmailId(String emailId) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {
            List<Booking> bookingList = bookingRepository.findByEmailId(emailId);
            if (!CollectionUtils.isEmpty(bookingList)) {
                List<BookingDto> bookingDtoList = bookingList
                        .stream()
                        .map(booking -> toBookingDto(booking))
                        .collect(Collectors.toList());
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(bookingDtoList);
            } else {
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }

    public  ResponseDataDto cancelTicketByPnrNumber(String pnrNumber) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {
            Booking booking = bookingRepository.findByPnrNumber(pnrNumber);
            if (Objects.nonNull(booking)) {
                booking.setStatus("Cancelled");
                Booking savedBooking = bookingRepository.save(booking);
                responseDataDto.setMessage(CommonConstants.SUCCESS);
            } else {
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;
    }

    private Passenger toPassengerEntity(PassengerDto passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerDto.getName());
        passenger.setGender(passengerDto.getGender());
        passenger.setAge(passengerDto.getAge());
        return passenger;
    }

    private Booking toBookingEntity(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setFlightId(bookingDto.getFlightId());
        booking.setName(bookingDto.getName());
        booking.setEmailId(bookingDto.getEmailId());
        booking.setNoOfSeats(bookingDto.getNoOfSeats());
        booking.setMeal(bookingDto.getMeal());
        booking.setStatus("Booked");
        //booking.setSeatNos(bookingDto.getSeatNos());

        booking.setPassengers(new LinkedHashSet<>());

        bookingDto.getPassengerDto().forEach(passengerDto -> {
            Passenger passenger = new Passenger();
            passenger.setName(passengerDto.getName());
            passenger.setGender(passengerDto.getGender());
            passenger.setAge(passengerDto.getAge());

            booking.getPassengers().add(passenger);
        });

        return booking;
    }


    private BookingDto toBookingDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(booking.getId());
        bookingDto.setFlightId(booking.getFlightId());
        bookingDto.setName(booking.getName());
        bookingDto.setEmailId(booking.getEmailId());
        bookingDto.setNoOfSeats(booking.getNoOfSeats());
        bookingDto.setMeal(booking.getMeal());
        //bookingDto.setSeatNos(booking.getSeatNos());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setPnrNumber(booking.getPnrNumber());
        bookingDto.setPassengerDto(new LinkedHashSet<>());

        booking.getPassengers().forEach(passenger -> {
            PassengerDto passengerDto = new PassengerDto();
            passengerDto.setName(passenger.getName());
            passengerDto.setGender(passenger.getGender());
            passengerDto.setAge(passenger.getAge());

            bookingDto.getPassengerDto().add(passengerDto);
        });
        return  bookingDto;
    }


}
