package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


@Entity
@Data
public class Advertisement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "aircraft_id", referencedColumnName = "id", nullable = false)
    private Aircraft aircraft;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "region")
    private String region;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "advertisement")
    private List<Gallery> images = new ArrayList<>();
}
