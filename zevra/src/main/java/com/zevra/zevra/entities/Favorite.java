package com.zevra.zevra.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    //TODO : faire les liens avec les autres tables
    @Column(nullable = false)
    private User user_id;

    @Column(nullable = false)
    private Exercice exercice;

    @Column(nullable = false)
    private Date created_at;

    @Column(nullable = false)
    private Date updated_at;
}
