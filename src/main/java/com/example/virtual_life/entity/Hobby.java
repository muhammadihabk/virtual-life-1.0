package com.example.virtual_life.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hobby {
    @Id
    private Long id;

    @Column(name = "hobby_name")
    private String name;
}
