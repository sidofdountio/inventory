package com.sidof.api;

import com.sidof.model.Product;
import com.sidof.model.Supplier;
import com.sidof.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RestController @RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600, methods ={
        DELETE, GET, OPTIONS, POST, PUT
})
public class SupplierApi {
    private final SupplierService supplierService;

    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>>supplierList(){
        final List<Supplier> suppliers = supplierService.SUPPLIERS()    ;
        return new ResponseEntity<>(suppliers, OK);
    }
@CrossOrigin(methods = POST)
    @PostMapping("/addSupplier")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplierToVave){
        final Supplier supplier = supplierService.addSupplier(supplierToVave);
        return  new ResponseEntity<>(supplier,CREATED);
    }
}
