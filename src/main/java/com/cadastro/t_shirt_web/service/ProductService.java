package com.cadastro.t_shirt_web.service;

import com.cadastro.t_shirt_web.dto.ProductRequestDTO;
import com.cadastro.t_shirt_web.dto.ProductResponseDTO;
import com.cadastro.t_shirt_web.entity.Category;
import com.cadastro.t_shirt_web.entity.Product;
import com.cadastro.t_shirt_web.exception.ProductNotFoundException;
import com.cadastro.t_shirt_web.repository.CategoryRepository;
import com.cadastro.t_shirt_web.repository.ProductRepository;
import com.cadastro.t_shirt_web.security.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    // CREATE
    public ProductResponseDTO create(ProductRequestDTO dto) {

        Category category = findCategoryById(dto.categoryId());

        Product product = productMapper.toEntity(dto, category);

        Product saved = productRepository.save(product);

        return productMapper.toResponse(saved);
    }

    // FIND ALL
    public List<ProductResponseDTO> findAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    // FIND BY ID
    public ProductResponseDTO findById(Long id) {

        Product product = findProductById(id);

        return productMapper.toResponse(product);
    }

    // UPDATE
    public ProductResponseDTO update(Long id,
                                     ProductRequestDTO dto) {

        Product product = findProductById(id);

        Category category = findCategoryById(dto.categoryId());

        productMapper.updateEntity(product, dto, category);

        Product updated = productRepository.save(product);

        return productMapper.toResponse(updated);
    }

    // DELETE
    public void delete(Long id) {

        Product product = findProductById(id);

        productRepository.delete(product);
    }

    private Product findProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id));
    }

    private Category findCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found: " + id));
    }
}