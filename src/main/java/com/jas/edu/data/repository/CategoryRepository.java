package com.jas.edu.data.repository;

import com.jas.edu.data.entity.Category;
import com.jas.edu.data.entity.Tamil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends
        JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {
}
