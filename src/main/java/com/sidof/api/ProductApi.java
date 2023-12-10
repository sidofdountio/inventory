package com.sidof.api;

import com.sidof.model.Product;
import com.sidof.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RequiredArgsConstructor
@Slf4j
public class ProductApi {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProducts(){
        final List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product>getProduct(@PathVariable("id")Long id){
        final Product products = productService.getProductById(id);
        return new ResponseEntity<>(products, OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product>saveProduct(@RequestBody Product productToSave){
        productToSave.setCode(String.valueOf(Math.random()));
        final Product product = productService.addProduct(productToSave);
        return new ResponseEntity<>(product, CREATED);
    }
        @PutMapping("/editProduct")
    public ResponseEntity<Product>editeProduct(@RequestBody Product productToEdite){
        return new ResponseEntity<>(productService.updateProduct(productToEdite), CREATED);
    }
}
