package com.cadastro.t_shirt_web.service;

import com.cadastro.t_shirt_web.dto.ProductRequestDTO;
import com.cadastro.t_shirt_web.entity.Product;
import com.cadastro.t_shirt_web.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE
    public Product create(ProductRequestDTO dto) {

        Product product = new Product();

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());

        return productRepository.save(product);
    }

    // READ ALL
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // READ BY ID
    public Product getById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product with id " + id + " not found"
                        )
                );
    }

    // UPDATE
    public Product update(Long id, ProductRequestDTO dto) {

        Product existing = getById(id);

        existing.setName(dto.name());
        existing.setDescription(dto.description());
        existing.setPrice(dto.price());
        existing.setStock(dto.stock());

        return productRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {

        productRepository.deleteById(id);
    }
}