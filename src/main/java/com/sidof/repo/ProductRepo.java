package com.sidof.repo;

import com.sidof.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author sidof
 * @Since 20/05/2023
 */

public interface ProductRepo extends JpaRepository<Product,Long> {
}
