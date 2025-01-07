package com.jas.edu.data.repository;

import com.jas.edu.data.entity.Currentevents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CurrentEventsrepository extends
        JpaRepository<Currentevents, String>, JpaSpecificationExecutor<Currentevents> {
}
