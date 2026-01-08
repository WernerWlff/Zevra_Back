package com.zevra.zevra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "exercices")
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference("exercice-type")
    @JoinColumn(nullable = false, name = "type_id")
    private Type type;

    @ManyToOne
    @JsonBackReference("exercice-type")
    @JoinColumn(nullable = false, name = "muscle_id")
    private Muscle muscle;

    @Column(nullable = false)
    private Timestamp duration;

    @Column(nullable = true)
    private int beginner_rep;

    @Column(nullable = true)
    private int intermediate_rep;

    @Column(nullable = true)
    private int har_rep;

    @Column(nullable = false)
    private Date created_at;

    @Column(nullable = false)
    private Date updated_at;
}
