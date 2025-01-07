package com.jas.edu.data.repository;

import com.jas.edu.data.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartRepository  extends
        JpaRepository<Part, String>, JpaSpecificationExecutor<Part> {
}
