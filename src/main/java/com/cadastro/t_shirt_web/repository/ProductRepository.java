package com.cadastro.t_shirt_web.repository;

import com.cadastro.t_shirt_web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}