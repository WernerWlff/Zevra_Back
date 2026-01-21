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

  @Column(name = "muscle_targeted", nullable = false)
  private String muscleTargeted;

  @Column(nullable = false)
  private String area;

  @OneToMany(mappedBy = "muscle")
  @JsonManagedReference("exercice-muscle")
  private List<Exercice> exercices;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMuscleTargeted() {
    return muscleTargeted;
  }

  public void setMuscleTargeted(String muscleTargeted) {
    this.muscleTargeted = muscleTargeted;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public List<Exercice> getExercices() {
    return exercices;
  }

  public void setExercices(List<Exercice> exercices) {
    this.exercices = exercices;
  }
}
