package com.cadastro.t_shirt_web.security.mapper;

import com.cadastro.t_shirt_web.dto.ProductRequestDTO;
import com.cadastro.t_shirt_web.dto.ProductResponseDTO;
import com.cadastro.t_shirt_web.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());

        return product;
    }

    public ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }

    public void updateEntity(Product product, ProductRequestDTO dto) {
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
    }
}