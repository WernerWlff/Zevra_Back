package com.zevra.zevra.controllers;

import com.zevra.zevra.dto.CreateExerciceRequest;
import com.zevra.zevra.dto.UpdateExerciceRequest;
import com.zevra.zevra.entities.Exercice;
import com.zevra.zevra.services.ExerciceService;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercices")
public class ExerciceController {
  private final ExerciceService exerciceService;

  public ExerciceController(ExerciceService exerciceService) {
    this.exerciceService = exerciceService;
  }

  @GetMapping
  public ResponseEntity<List<Exercice>> getAllExercices() {
    return new ResponseEntity<>(exerciceService.getAllExercices(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Exercice> getExerciceById(@PathVariable Long id) {
    Optional<Exercice> exercice = exerciceService.getExerciceById(id);

    if (exercice.isPresent()) {
      return new ResponseEntity<>(exercice.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> createExercice( @Valid @RequestBody CreateExerciceRequest request) {
    try {
      Exercice exercice = exerciceService.createExercice(request);
      return new ResponseEntity<>(exercice, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateExercice(
      @PathVariable Long id, @Valid @RequestBody UpdateExerciceRequest request) {
    try {
      Optional<Exercice> exercice = exerciceService.updateExercice(id, request);

      if (exercice.isPresent()) {
        return new ResponseEntity<>(exercice.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteExercice(@PathVariable Long id) {
    Optional<Exercice> deletedExercice = exerciceService.deleteExercice(id);

    if (deletedExercice.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
