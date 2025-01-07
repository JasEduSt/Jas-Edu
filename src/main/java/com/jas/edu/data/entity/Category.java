package com.jas.edu.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cat_id;
    private String category_name;
    //private String part_id;

}
