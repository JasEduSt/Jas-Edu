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

public interface TamilRepository
        extends
        JpaRepository<Tamil, Long>, JpaSpecificationExecutor<Tamil> {

    //@Query( value = "SELECT tamil.tg_id, tamil.cat_id, tamil.ch_id, tamil.std_id, tamil.part_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer FROM tamil ,standard ,part ,chapter ,category WHERE tamil.cat_id = category.cat_id AND tamil.ch_id = chapter.ch_id AND chapter.std_id = standard.std_id AND category.part_id = part.part_id", nativeQuery = true)
    //Page<Tamil> findAllGuides(final Pageable pageable);

    //@Query( value = "SELECT DISTINCT tamil.tg_id, tamil.tg_question, tamil.tg_answer, tamil.cat_id, tamil.ch_id, category.category_name, chapter.chapter_value FROM tamil FULL OUTER JOIN category on Lower(tamil.cat_id) = Lower(category.cat_id) FULL OUTER JOIN chapter on Lower(tamil.ch_id) = Lower(chapter.ch_id) order by tg_id", nativeQuery = true)
    //Page<Tamil> findAllGuides(final Pageable pageable);
    @Query( value = "SELECT DISTINCT tamil.tg_id, tamil.tg_question, tamil.tg_answer, tamil.cat_id, tamil.ch_id, tamil.std_id, tamil.part_id, standard.standard_value, part.part_value, category.category_name, chapter.chapter_value FROM tamil FULL OUTER JOIN standard on Lower(tamil.std_id) = Lower(standard.std_id) FULL OUTER JOIN part on Lower(tamil.part_id) = Lower(part.part_id) FULL OUTER JOIN category on Lower(tamil.cat_id) = Lower(category.cat_id) FULL OUTER JOIN chapter on Lower(tamil.ch_id) = Lower(chapter.ch_id) order by tg_id", nativeQuery = true)
    Page<Tamil> findAllGuides(final Pageable pageable);
     /*@Query( value = "SELECT tamil.tg_id, tamil.cat_id, tamil.ch_id, tamil.std_id, tamil.part_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer "
            + "FROM tamil , standard , part , chapter , category  WHERE tamil.cat_id = category.cat_id AND tamil.ch_id = chapter.ch_id AND "
            + "tamil.std_id = standard.std_id AND tamil.part_id = part.part_id ", nativeQuery = true)
    Page<Tamil> findAllGuides(final Pageable pageable);

    @Query( value = "SELECT tamil.tg_id, tamil.cat_id, tamil.ch_id, tamil.std_id, tamil.part_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer FROM tamil, standard, part, chapter, category WHERE tamil.cat_id = category.cat_id AND tamil.std_id = standard.std_id AND tamil.part_id = part.part_id", nativeQuery = true)
    Page<Tamil> findAllGuides(final Pageable pageable);*/
    @Query( value = "SELECT DISTINCT tamil.tg_id, tamil.tg_question, tamil.tg_answer, tamil.cat_id, tamil.ch_id, tamil.std_id, tamil.part_id, standard.standard_value, part.part_value, category.category_name, chapter.chapter_value FROM tamil FULL OUTER JOIN standard on Lower(tamil.std_id) = Lower(standard.std_id) FULL OUTER JOIN part on Lower(tamil.part_id) = Lower(part.part_id) FULL OUTER JOIN category on Lower(tamil.cat_id) = Lower(category.cat_id) FULL OUTER JOIN chapter on Lower(tamil.ch_id) = Lower(chapter.ch_id) where tamil.tg_id=? ", nativeQuery = true)
    Optional<Tamil> findById(Long aLong);

    @Query("SELECT new com.jas.edu.data.entity.Tamil(t.tg_question, t.tg_answer) "
            + "FROM Tamil t where Lower(t.ch_id) = :chid")
    List<Tamil> findChapters(@Param("chid") String chid);
    //Tamil update(Tamil entity);

    @Query(nativeQuery = true, value = "SELECT tamil.tg_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer "
            + " FROM tamil , category , part , chapter , standard  WHERE tamil.cat_id = category.cat_id AND category.part_id = part.part_id AND tamil.ch_id = chapter.ch_id AND chapter.std_id = standard.std_id" ,
            countQuery = "SELECT count(*) tamil.tg_id, standard.standard_value, part.part_value, chapter.chapter_value, category.category_name, tamil.tg_question, tamil.tg_answer "
                    + " FROM tamil , category , part , chapter , standard  WHERE tamil.cat_id = category.cat_id AND category.part_id = part.part_id AND tamil.ch_id = chapter.ch_id AND chapter.std_id = standard.std_id" )
    List<Tamil> findAllGuides();
}
