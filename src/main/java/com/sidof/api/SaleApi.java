package com.sidof.api;

import com.sidof.model.Inventory;
import com.sidof.model.Sale;
import com.sidof.operation.InventoryOperation;
import com.sidof.service.InventoryService;
import com.sidof.service.ProductService;
import com.sidof.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController @RequestMapping("/api/v1/inventory") @CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600) @RequiredArgsConstructor @Transactional
public class SaleApi {
    private final SaleService saleService;
    private final InventoryService inventoryService;
    private final ProductService productService;

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>>getSales(){
        final List<Sale> sales = saleService.SALES();
        return new ResponseEntity<>(sales, OK);
    }

    @PostMapping("/addSale")
    public ResponseEntity<Sale>addSale(@RequestBody Sale saleToSave ){
        final List<Inventory> inventoryList = inventoryService.INVENTORIES();
        final Sale sale = saleService.addSale(saleToSave);
        InventoryOperation inventoryOperation = new InventoryOperation(productService);
        final Inventory cmupForSale = inventoryOperation.cmupForSale("Sale", sale,inventoryList);
        inventoryService.addInventory(cmupForSale);
        return new ResponseEntity<>(sale, CREATED);
    }
}