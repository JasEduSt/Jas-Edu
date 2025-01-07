package com.jas.edu.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "tnpscns")
public class Tnpscns implements Serializable {
    @Id
    private String tnnotify_id;
    private String tnnotify_subject;
    private String regPeriod;
    private String examDate;
    private String url;

    public String getUrl() {
        return url;
    }
}
