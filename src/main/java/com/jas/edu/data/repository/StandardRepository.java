package com.jas.edu.data.repository;

import com.jas.edu.data.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StandardRepository extends
        JpaRepository<Standard, String> , JpaSpecificationExecutor<Standard>{

}
