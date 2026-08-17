package com.cadastro.t_shirt_web.repository;

import com.cadastro.t_shirt_web.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}