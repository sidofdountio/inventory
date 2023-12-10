package com.sidof.api;

import com.sidof.model.Customer;
import com.sidof.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600, methods ={
        DELETE, GET, OPTIONS, POST, PUT
})
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        final List<Customer> customers = customerService.CUSTOMERS();
        return new ResponseEntity<>(customers, OK);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customerToSave) {
        final Customer customer = customerService.addCustomer(customerToSave);
        return new ResponseEntity<>(customer, CREATED);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        final Customer customers = customerService.getCustomerById(id);
        return new ResponseEntity<>(customers, OK);
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable Long id) {
        final Boolean isDeleted = customerService.DeleteCustomer(id);
        return new ResponseEntity<>(isDeleted, OK);
    }
}
