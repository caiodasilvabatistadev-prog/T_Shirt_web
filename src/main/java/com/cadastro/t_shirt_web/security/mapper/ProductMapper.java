package com.cadastro.t_shirt_web.security.mapper;

import com.cadastro.t_shirt_web.dto.ProductRequestDTO;
import com.cadastro.t_shirt_web.dto.ProductResponseDTO;
import com.cadastro.t_shirt_web.entity.Category;
import com.cadastro.t_shirt_web.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto, Category category) {

        Product product = new Product();

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setCategory(category);

        return product;
    }

    public ProductResponseDTO toResponse(Product product) {

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory() != null
                        ? product.getCategory().getId()
                        : null
        );
    }

    public void updateEntity(
            Product product,
            ProductRequestDTO dto,
            Category category
    ) {

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setCategory(category);
    }
}