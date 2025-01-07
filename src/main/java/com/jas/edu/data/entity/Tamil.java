package com.jas.edu.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@NoArgsConstructor
@Data
@Table(name="tamil")
public class Tamil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long tg_id;
    @Column(columnDefinition = "TEXT")
    private String tg_question;
    @Column(columnDefinition = "TEXT")
    private String tg_answer;
    private String cat_id;
    private String ch_id;
    private String std_id;
    private String part_id;
    private String standard_value;
    private String part_value;
    private String chapter_value;
    private String category_name;

    public Tamil(Long tg_id, String tg_question, String tg_answer, String cat_id, String ch_id, String std_id, String part_id, String standard_value, String part_value, String chapter_value, String category_name) {
        this.tg_id = tg_id;
        this.tg_question = tg_question;
        this.tg_answer = tg_answer;
        this.cat_id = cat_id;
        this.ch_id = ch_id;
        this.std_id = std_id;
        this.part_id = part_id;
        this.standard_value = standard_value;
        this.part_value = part_value;
        this.chapter_value = chapter_value;
        this.category_name = category_name;
    }

    public Tamil(String tg_question, String tg_answer) {
        this.tg_question = tg_question;
        this.tg_answer = tg_answer;
    }
}
