package com.sidof.repo;

import com.sidof.model.InvoiceSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InvoiceSaleRepo extends JpaRepository<InvoiceSale,Long> {
    @Override
    @Query("SELECT i FROM InvoiceSale i  ORDER BY id")
    List<InvoiceSale> findAll();
    List<InvoiceSale> findBySaleId(Long saleId);
    List<InvoiceSale> findByCustomerId(Long customerId);
}
