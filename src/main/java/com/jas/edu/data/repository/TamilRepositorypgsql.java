package com.jas.edu.data.repository;


import com.jas.edu.data.entity.Tamil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TamilRepositorypgsql
        extends
        JpaRepository<Tamil, Long>, JpaSpecificationExecutor<Tamil> {
    /*@Query( value = "SELECT tamil.tg_id, tamil.cat_id, tamil.ch_id, tamil.std_id, tamil.part_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer "
            + "FROM tamil , standard , part , chapter , category  WHERE tamil.cat_id = category.cat_id AND tamil.ch_id = chapter.ch_id AND "
            + "tamil.std_id = standard.std_id AND tamil.part_id = part.part_id ", nativeQuery=true)
    Page<Tamil> findAllGuides(final Pageable pageable);*/

    @Query( value = "SELECT t.tg_id, t.cat_id, t.ch_id, t.std_id, t.part_id, s.standard_value, p.part_value, c.chapter_value, a.category_name, t.tg_question, t.tg_answer "
            + "FROM tamil t "
            + "INNER JOIN standard s USING (std_id) "
            + "INNER JOIN part p USING (part_id) "
            + "INNER JOIN chapter c USING (ch_id) "
            + "INNER JOIN category a USING (cat_id) ", nativeQuery=true)
    Page<Tamil> findAllGuides(final Pageable pageable);

    @Query( value = "SELECT tamil.tg_id, tamil.cat_id, tamil.ch_id, tamil.std_id, tamil.part_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer "
            + "FROM tamil, standard , part , chapter , category  where tamil.tg_id = :id AND tamil.cat_id = category.cat_id AND tamil.ch_id = chapter.ch_id AND "
            + "tamil.std_id = standard.std_id AND tamil.part_id = part.part_id ", nativeQuery=true)
    Optional<Tamil> findById(Long id);

    /*@Query(value = "SELECT new com.jas.edu.data.entity.Tamil(t.tg_question, t.tg_answer) "
            + "FROM Tamil t where t.ch_id = :chid ")
    List<Tamil> findChapters(@Param("chid") String chid);*/

    @Query(value = "SELECT t.tg_question, t.tg_answer "
            + "FROM com.jas.edu.data.entity.Tamil t where t.ch_id = :chid ")
    List<Tamil> findChapters(@Param("chid") String chid);

    @Query( value = "SELECT tamil.tg_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer "
            + " FROM tamil , category , part , chapter , standard  WHERE tamil.cat_id = category.cat_id AND category.part_id = part.part_id AND tamil.ch_id = chapter.ch_id AND chapter.std_id = standard.std_id" ,
            countQuery = "SELECT count(*) tamil.tg_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer "
                    + " FROM tamil , category , part , chapter , standard  WHERE tamil.cat_id = category.cat_id AND category.part_id = part.part_id AND tamil.ch_id = chapter.ch_id AND chapter.std_id = standard.std_id" , nativeQuery = true)
    List<Tamil> findAllGuides();
}
