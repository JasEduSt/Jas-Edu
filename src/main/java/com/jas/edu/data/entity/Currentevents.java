package com.jas.edu.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "currentevents")
public class Currentevents {
    @Id
    private Long ce_id;
    private String yearmonth;
    private String ce_question;
    private String ce_answer;

}
