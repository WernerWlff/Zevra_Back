package com.zevra.zevra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddExerciceToFavoriteRequest {

    @NotNull(message = "L'ID de l'exercice est obligatoire")
    private Long exercice_id;

    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    private String description;

    public Long getExercice_id() {
        return exercice_id;
    }

    public void setExercice_id(Long exercice_id) {
        this.exercice_id = exercice_id;
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
}