package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.InventoryDto;
import com.balamsd7.flightbooking.dto.InventorySearchDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.InventoryService;
import com.balamsd7.flightbooking.utils.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1.0/flight/airline/inventory")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDataDto> createInventory(@RequestBody InventoryDto inventoryDto){
        return  APIResponseBuilder.buildResponseFromDto(inventoryService.createInventory(inventoryDto));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseDataDto> updateInventory(@RequestBody InventoryDto inventoryDto){
        return  APIResponseBuilder.buildResponseFromDto(inventoryService.updateInventory(inventoryDto));
    }

    @DeleteMapping( "/delete")
    public ResponseEntity<ResponseDataDto> deleteById(@RequestParam(name = "id")   int inventoryId){
        return  APIResponseBuilder.buildResponseFromDto(inventoryService.deleteById(inventoryId));
    }

    @GetMapping("/getAllInventory")
    public ResponseEntity<ResponseDataDto> getAllInventory(){
        return  APIResponseBuilder.buildResponseFromDto(inventoryService.getAllInventory());
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDataDto> searchInventory(@RequestBody InventorySearchDto inventorySearchDto){
        return  APIResponseBuilder.buildResponseFromDto(inventoryService.searchInventory(inventorySearchDto));
    }



}
