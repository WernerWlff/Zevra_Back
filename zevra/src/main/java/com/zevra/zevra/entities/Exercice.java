package com.zevra.zevra.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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
  @JsonBackReference("exercice-muscle")
  @JoinColumn(nullable = false, name = "muscle_id")
  private Muscle muscle;

  @OneToMany(mappedBy = "exercice")
  @JsonManagedReference("favorite-exercice")
  private List<Favorite> favorites;

  @OneToMany(mappedBy = "exercice")
  @JsonManagedReference("training_list-exercice")
  private List<TrainingList> trainingLists;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Muscle getMuscle() {
    return muscle;
  }

  public void setMuscle(Muscle muscle) {
    this.muscle = muscle;
  }

  public List<Favorite> getFavorites() {
    return favorites;
  }

  public void setFavorites(List<Favorite> favorites) {
    this.favorites = favorites;
  }

  public List<TrainingList> getTrainingLists() {
    return trainingLists;
  }

  public void setTrainingLists(List<TrainingList> trainingLists) {
    this.trainingLists = trainingLists;
  }

  public Timestamp getDuration() {
    return duration;
  }

  public void setDuration(Timestamp duration) {
    this.duration = duration;
  }

  public int getBeginner_rep() {
    return beginner_rep;
  }

  public void setBeginner_rep(int beginner_rep) {
    this.beginner_rep = beginner_rep;
  }

  public int getIntermediate_rep() {
    return intermediate_rep;
  }

  public void setIntermediate_rep(int intermediate_rep) {
    this.intermediate_rep = intermediate_rep;
  }

  public int getHar_rep() {
    return har_rep;
  }

  public void setHar_rep(int har_rep) {
    this.har_rep = har_rep;
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
}
