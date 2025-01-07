package com.jas.edu.data.repository;

import com.jas.edu.data.entity.Chapter;
import com.jas.edu.data.entity.Tamil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterRepository extends
        JpaRepository<Chapter, String>, JpaSpecificationExecutor<Chapter> {

    @Query("SELECT new com.jas.edu.data.entity.Chapter(c.ch_id, c.chapter_value) "
            + "FROM Chapter c where c.std_id = :stdid")
    Page<Chapter> findById(final Pageable pageable, @Param("stdid") String stdid);
}
