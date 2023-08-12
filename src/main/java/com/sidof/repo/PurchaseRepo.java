package com.sidof.repo;

import com.sidof.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Long> {
}
