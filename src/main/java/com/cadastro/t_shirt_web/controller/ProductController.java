package com.cadastro.t_shirt_web.controller;

import com.cadastro.t_shirt_web.dto.ProductRequestDTO;
import com.cadastro.t_shirt_web.entity.Product;
import com.cadastro.t_shirt_web.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE PRODUCT
    @PostMapping
    public ResponseEntity<Product> create(
            @Valid @RequestBody ProductRequestDTO dto
    ) {

        Product product = productService.create(dto);

        return ResponseEntity.ok(product);
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        List<Product> products = productService.getAll();

        return ResponseEntity.ok(products);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(
            @PathVariable Long id
    ) {

        Product product = productService.getById(id);

        return ResponseEntity.ok(product);
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO dto
    ) {

        Product updatedProduct = productService.update(id, dto);

        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}