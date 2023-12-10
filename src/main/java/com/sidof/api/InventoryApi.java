package com.sidof.api;

import com.sidof.model.Inventory;
import com.sidof.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600, methods ={
        DELETE, GET, OPTIONS, POST, PUT
})
public class InventoryApi {
    private final InventoryService inventoryService;


    @GetMapping("/inventories")
    public ResponseEntity<List<Inventory>>getInventory(){
        final List<Inventory> inventories = inventoryService.INVENTORIES();
        return new ResponseEntity<>(inventories, OK);
    }

    @PostMapping("/inventory/addInventory")
    public ResponseEntity<Inventory>saveInventory(@RequestBody Inventory inventoryTosave){
        var inventory = inventoryService.addInventory(inventoryTosave);
        return new ResponseEntity<>( inventory,CREATED);
    }

    @PutMapping("/inventory/updateInventory")
    public ResponseEntity<Inventory>UpdateInventory(@RequestBody Inventory inventoryToEdit){
        var inventory = inventoryService.addInventory(inventoryToEdit);
        return new ResponseEntity<>( inventory,CREATED);
    }
}
