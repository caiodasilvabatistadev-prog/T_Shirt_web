package com.cadastro.t_shirt_web.service;

import com.cadastro.t_shirt_web.dto.ProductRequestDTO;
import com.cadastro.t_shirt_web.dto.ProductResponseDTO;
import com.cadastro.t_shirt_web.entity.Product;
import com.cadastro.t_shirt_web.exception.ProductNotFoundException;
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
    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product product = toEntity(dto);
        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    // READ ALL
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // READ BY ID
    public ProductResponseDTO getById(Long id) {
        Product product = findProductById(id);
        return toResponseDTO(product);
    }

    // UPDATE
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = findProductById(id);

        updateEntity(product, dto);

        Product updated = productRepository.save(product);
        return toResponseDTO(updated);
    }

    // DELETE
    public void delete(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    // -----------------------
    // PRIVATE METHODS
    // -----------------------

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        return product;
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    private void updateEntity(Product product, ProductRequestDTO dto) {
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
    }
}