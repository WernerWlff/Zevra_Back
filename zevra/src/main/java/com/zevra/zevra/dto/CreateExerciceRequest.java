package com.zevra.zevra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CreateExerciceRequest {

  @NotBlank(message = "Le nom est obligatoire")
  private String name;

  @NotNull(message = "Le type est obligatoire")
  private Long type_id;

  @NotNull(message = "Le muscle est obligatoire")
  private Long muscle_id;

  @NotNull(message = "la durée est obligatoire")
  private Timestamp duration;

  private Integer beginner_rep;

  private Integer intermediate_rep;

  private Integer hard_rep;

  public Long getType_id() {
    return type_id;
  }

  public void setType_id(Long type_id) {
    this.type_id = type_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getMuscle_id() {
    return muscle_id;
  }

  public void setMuscle_id(Long muscle_id) {
    this.muscle_id = muscle_id;
  }

  public Timestamp getDuration() {
    return duration;
  }

  public void setDuration(Timestamp duration) {
    this.duration = duration;
  }

  public Integer getBeginner_rep() {
    return beginner_rep;
  }

  public void setBeginner_rep(Integer beginner_rep) {
    this.beginner_rep = beginner_rep;
  }

  public Integer getIntermediate_rep() {
    return intermediate_rep;
  }

  public void setIntermediate_rep(Integer intermediate_rep) {
    this.intermediate_rep = intermediate_rep;
  }

  public Integer getHard_rep() {
    return hard_rep;
  }

  public void setHard_rep(Integer hard_rep) {
    this.hard_rep = hard_rep;
  }
}
