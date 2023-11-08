package com.sidof.service;

import com.sidof.model.Customer;
import com.sidof.repo.CustomerRepo;
import com.sidof.service.interfaceService.CustomerDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerService implements CustomerDao {
    private final CustomerRepo customerRepo;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public final Customer getCustomerById(Long customerId) {
        return customerRepo.findById(customerId).get();
    }

    @Override
    public Boolean DeleteCustomer(Long customerIdToDelete) {
        Boolean isDeleted = true;
        if (!customerRepo.existsById(customerIdToDelete)){
            isDeleted = false;
          throw new IllegalStateException(String.format("The provide customer %d not exist",customerIdToDelete));
        }
        customerRepo.deleteById(customerIdToDelete);
        return true;
    }

    @Override
    public List<Customer> CUSTOMERS() {
        return customerRepo.findAll();
    }
}
