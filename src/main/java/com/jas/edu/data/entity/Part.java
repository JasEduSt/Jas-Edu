package com.jas.edu.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "part")
public class Part implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String part_id;
    private String part_value;
}
