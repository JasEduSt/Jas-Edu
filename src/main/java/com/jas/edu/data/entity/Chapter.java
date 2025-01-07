package com.jas.edu.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="chapter")
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String ch_id;
    private String chapter_value;
    private String std_id;
    public Chapter() {
    }

    public Chapter(String ch_id, String chapter_value) {
        this.ch_id = ch_id;
        this.chapter_value = chapter_value;
    }
}
