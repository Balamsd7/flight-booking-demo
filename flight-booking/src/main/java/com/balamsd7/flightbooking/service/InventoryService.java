package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.Exception.BusinessException;
import com.balamsd7.flightbooking.dto.InventoryDto;
import com.balamsd7.flightbooking.dto.InventorySearchDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.Airline;
import com.balamsd7.flightbooking.model.Inventory;
import com.balamsd7.flightbooking.repository.InventoryRepository;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    public ResponseDataDto createInventory(InventoryDto inventoryDto) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{

            Inventory inventory = toInventoryEntity(inventoryDto);
            Inventory savedInventory = inventoryRepository.save(inventory);
            if(Objects.nonNull(savedInventory)){
                responseDataDto.setMessage(CommonConstants.SUCCESS);
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }

    public ResponseDataDto updateInventory(InventoryDto inventoryDto) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            Optional<Inventory> optional = inventoryRepository.findById(inventoryDto.getInventoryId());
            if(optional.isPresent()){
                Inventory existingInventory = optional.get();
                existingInventory.setAirLine(inventoryDto.getAirLine());
                existingInventory.setFlightNumber(inventoryDto.getFlightId());
                existingInventory.setFromPlace(inventoryDto.getFromPlace());
                existingInventory.setToPlace(inventoryDto.getToPlace());
                existingInventory.setStartDateTime(inventoryDto.getStartDateTime());
                existingInventory.setEndDateTime(inventoryDto.getEndDateTime());
                existingInventory.setScheduledDays(inventoryDto.getScheduledDays());
                existingInventory.setInstrument(inventoryDto.getInstrument());
                existingInventory.setTotalBusinessClassSeats(inventoryDto.getBusinessClassSeats());
                existingInventory.setTotalNonBusinessClassSeats(inventoryDto.getNonBusinessClassSeats());
                existingInventory.setTicketCost(inventoryDto.getTicketCost());
                existingInventory.setNumberOfRows(inventoryDto.getNumberOfRows());
                existingInventory.setMeal(inventoryDto.getMeal());
                Inventory savedInventory = inventoryRepository.save(existingInventory);
                if(Objects.nonNull(savedInventory)){
                    responseDataDto.setMessage(CommonConstants.SUCCESS);
                }else{
                    responseDataDto.setMessage(CommonConstants.NDF);
                }
            } else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;
    }

    public ResponseDataDto deleteById(int inventoryId) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            Optional<Inventory> optional = inventoryRepository.findById(inventoryId);
            if(optional.isPresent()){
                Inventory inventory = optional.get();
                if(Objects.nonNull(inventory)) {
                    inventoryRepository.delete(inventory);
                    responseDataDto.setMessage(CommonConstants.SUCCESS);
                }else{
                    responseDataDto.setMessage(CommonConstants.NDF);
                }
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }

    public ResponseDataDto getAllInventory() {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {
            List<Inventory> inventoryList = inventoryRepository.findAll();
            if (!CollectionUtils.isEmpty(inventoryList)) {
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(inventoryList);
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

    public  ResponseDataDto searchInventory(InventorySearchDto inventorySearchDto) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            List<Inventory> inventoryList = inventoryRepository.findByStartDateFromToPlace(inventorySearchDto.getDateTime(),inventorySearchDto.getFromPlace(), inventorySearchDto.getToPlace());
            if(!CollectionUtils.isEmpty(inventoryList)){
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(inventoryList);
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }

    private Inventory toInventoryEntity(InventoryDto inventoryDto) {
        Inventory inventory = new Inventory();
        inventory.setAirLine(inventoryDto.getAirLine());
        inventory.setFlightNumber(inventoryDto.getFlightId());
        inventory.setFromPlace(inventoryDto.getFromPlace());
        inventory.setToPlace(inventoryDto.getToPlace());
        inventory.setStartDateTime(inventoryDto.getStartDateTime());
        inventory.setEndDateTime(inventoryDto.getEndDateTime());
        inventory.setScheduledDays(inventoryDto.getScheduledDays());
        inventory.setInstrument(inventoryDto.getInstrument());
        inventory.setTotalBusinessClassSeats(inventoryDto.getBusinessClassSeats());
        inventory.setTotalNonBusinessClassSeats(inventoryDto.getNonBusinessClassSeats());
        inventory.setTicketCost(inventoryDto.getTicketCost());
        inventory.setNumberOfRows(inventoryDto.getNumberOfRows());
        inventory.setMeal(inventoryDto.getMeal());
        return inventory;
    }
}
