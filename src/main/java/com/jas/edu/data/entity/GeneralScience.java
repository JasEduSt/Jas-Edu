package com.jas.edu.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "generalscience")
public class GeneralScience {
    @Id
    private Long gs_id;
    private String gs_chapter;
    private String gs_question;
    private String gs_answer;

    public GeneralScience(Long gs_id, String chapter, String question, String answer) {
        this.gs_id = gs_id;
        this.gs_chapter = chapter;
        this.gs_question = question;
        this.gs_answer = answer;
    }

    public GeneralScience() {
    }

    public GeneralScience(String question, String answer) {
        this.gs_question = question;
        this.gs_answer = answer;
    }
}

