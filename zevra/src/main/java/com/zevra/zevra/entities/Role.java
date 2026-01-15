package com.zevra.zevra.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "role")
    @JsonManagedReference("user-role")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
