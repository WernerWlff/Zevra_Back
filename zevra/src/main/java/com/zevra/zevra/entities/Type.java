package com.zevra.zevra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @OneToMany
    @JsonBackReference("exercice-type")
    @Column(nullable = false)
    private List<Exercice> exercices;
}
