package com.example.ProductCategory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ProductCategory.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Long> {

}
