package com.sidof.service.interfaceService;

import com.sidof.model.Customer;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
public interface CustomerDao {
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer getCustomerById(Long customerId);
    Boolean DeleteCustomer(Long customerIdToDelete);
    List<Customer>CUSTOMERS();
}
