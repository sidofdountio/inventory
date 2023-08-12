package com.sidof.repo;

import com.sidof.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByProductNameAndIsUp(String productName,boolean isUp);
}
