package com.zevra.zevra.dto;

import java.util.Date;

public class FavoriteResponse {

    private Long id;
    private Long exerciceId;
    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;
    private String exerciceType;
    private String exerciceMuscle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(Long exerciceId) {
        this.exerciceId = exerciceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getExerciceType() {
        return exerciceType;
    }

    public void setExerciceType(String exerciceType) {
        this.exerciceType = exerciceType;
    }

    public String getExerciceMuscle() {
        return exerciceMuscle;
    }

    public void setExerciceMuscle(String exerciceMuscle) {
        this.exerciceMuscle = exerciceMuscle;
    }
}
