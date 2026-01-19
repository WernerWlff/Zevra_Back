package com.zevra.zevra.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "muscles")
public class Muscle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String category;

  @OneToMany(mappedBy = "muscle")
  @JsonManagedReference("exercice-muscle")
  private List<Exercice> exercices;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public List<Exercice> getExercices() {
    return exercices;
  }

  public void setExercices(List<Exercice> exercices) {
    this.exercices = exercices;
  }
}
