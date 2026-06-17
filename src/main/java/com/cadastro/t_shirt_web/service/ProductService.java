package com.cadastro.t_shirt_web.service;

import com.cadastro.t_shirt_web.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.cadastro.t_shirt_web.entity.Product;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Product create(Product product) {
        return repository.save(product);
    }

    // READ ALL
    public List<Product> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // UPDATE
    public Product update(Long id, Product product) {

        Product existing = getById(id);

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setDescription(product.getDescription());

        return repository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}