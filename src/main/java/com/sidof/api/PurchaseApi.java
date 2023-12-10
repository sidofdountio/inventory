package com.sidof.api;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */

import com.sidof.model.Inventory;
import com.sidof.model.Purchase;
import com.sidof.model.enumes.Status;
import com.sidof.operation.InventoryOperation;
import com.sidof.service.InventoryService;
import com.sidof.service.ProductService;
import com.sidof.service.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.sidof.model.enumes.Status.*;
import static java.time.LocalDate.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController @RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@Transactional
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600, methods ={
        DELETE, GET, OPTIONS, POST, PUT
})
public class PurchaseApi {
    private final PurchaseService purchaseService;
    private final InventoryService inventoryService;
    private final ProductService productService;
    private final InventoryOperation inventoryOperation;


    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getPurchases(){
        final List<Purchase> purchases = purchaseService.PURCHASES();
        return new ResponseEntity<>(purchases, OK);
    }

    @PostMapping("/addPurchase")
    public ResponseEntity<Purchase>saveProduct(@RequestBody Purchase purchaseToSave){
        final List<Inventory> inventoryList = inventoryService.INVENTORIES();

        purchaseToSave.setOrderAt(now());
        purchaseToSave.setStatus(INPROGRESS);
        final int quantity = purchaseToSave.getQuantity();
        final double price = purchaseToSave.getPrice();
        final double amount =  (double) quantity * price;
        purchaseToSave.setAmount(amount);
//        TODO:check arrived date and the status.
        final Purchase purchase = purchaseService.addPurchase(purchaseToSave);
        final Inventory inventorySave = inventoryOperation.cmupForPurchase("Purchase", purchase,inventoryList);
        inventoryService.addInventory(inventorySave);
        return new ResponseEntity<>(purchase, CREATED);

    }
}
