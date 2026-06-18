package com.cadastro.t_shirt_web.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="Name cannot be empty")
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank(message= "Description cannot be empty")
    @Size(min = 10,max = 255)
    private String description;

    @NotNull(message="Price is required")
    @Positive(message="Price must be greater than zero")
    private BigDecimal price;

    public Product(){

}
    public Product(
            Long id,
            String name,
            String description,
            BigDecimal price
    )
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price= price;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

