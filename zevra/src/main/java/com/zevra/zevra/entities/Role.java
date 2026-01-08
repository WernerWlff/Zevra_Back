package com.zevra.zevra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO : Regarder si il faut changer le nom de la colonne pour qu'il n'y ai pas de confusion avec la table
    @Column(nullable = false, unique = true)
    private String permission;

    // permet de récupérer la listes des users avec les rôles
    @OneToMany
    @JsonBackReference("user-role")
    @Column(nullable = false)
    private List<User> users;
}
