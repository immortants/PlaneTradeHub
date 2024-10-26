package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
@Entity
@Data
public class Gallery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Lob
    @Column(columnDefinition = "LONGBLOB", name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", referencedColumnName = "id")
    private Advertisement advertisement;
}
