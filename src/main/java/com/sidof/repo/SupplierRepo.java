package com.sidof.repo;

import com.sidof.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Long> {
}
