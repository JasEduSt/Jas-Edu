package com.jas.edu.data.repository;


import com.jas.edu.data.entity.GeneralScience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeneralScienceRepository extends
        JpaRepository<GeneralScience, Long>,
        JpaSpecificationExecutor<GeneralScience> {
    @Query("SELECT distinct gs_chapter from GeneralScience")
    List getChapters();

    @Query("SELECT new com.jas.edu.data.entity.GeneralScience(s.gs_question,s.gs_answer) FROM GeneralScience s where s.gs_chapter = :chvalue")
    List<GeneralScience> getAllByName(@Param("chvalue") String chvalue);

}