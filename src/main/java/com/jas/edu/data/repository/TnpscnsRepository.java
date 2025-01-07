package com.jas.edu.data.repository;

import com.jas.edu.data.entity.Tnpscns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TnpscnsRepository extends
        JpaRepository<Tnpscns, String>, JpaSpecificationExecutor<Tnpscns> {
}
