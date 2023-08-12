package com.sidof.service;

import com.sidof.model.Product;
import com.sidof.repo.ProductRepo;
import com.sidof.service.interfaceService.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService implements ProductDao {
    private final ProductRepo productRepo;

    @Override
    public Product addProduct(Product product) {
        log.info("Saving new product {}", product);
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        log.info("Updating new product {}", product);
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        log.info("Fecthing product by {}", productId);
        return productRepo.findById(productId).get();
    }

    public boolean existProduct(Long productId) {
        final boolean existsById = productRepo.existsById(productId);
        if (!existsById) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteProduct(Long productToDelete) {
        log.info("Delete product by {}", productToDelete);
        productRepo.deleteById(productToDelete);
    }

    @Override
    public List<Product> getProducts() {
        log.info("Fecthing product");
        return productRepo.findAll();
    }
}