package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Aircraft {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "manufacturer")
    private String manufacturer;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "run")
    private int run;
    @Basic
    @Column(name = "year")
    private int year;
    @Basic
    @Column(name = "color")
    private String color;
    @OneToOne(mappedBy = "aircraft")
    private Advertisement advertisement;
}
