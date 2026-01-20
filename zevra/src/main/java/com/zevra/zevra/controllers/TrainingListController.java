package com.zevra.zevra.controllers;

import com.zevra.zevra.dto.AddExerciseToTrainingListRequest;
import com.zevra.zevra.entities.TrainingList;
import com.zevra.zevra.services.TrainingListService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training-lists")
public class TrainingListController {

  private final TrainingListService trainingListService;

  public TrainingListController(TrainingListService training_listService) {
    this.trainingListService = training_listService;
  }

  @GetMapping
  public ResponseEntity<List<TrainingList>> getAllTrainingLists() {
    List<TrainingList> trainingLists = trainingListService.findAllTrainingList();
    return new ResponseEntity<>(trainingLists, HttpStatus.OK);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<TrainingList>> getTrainingListsByUser(@PathVariable UUID userId) {
    List<TrainingList> trainingLists = trainingListService.findTrainingListByUserId(userId);
    return new ResponseEntity<>(trainingLists, HttpStatus.OK);
  }

  @GetMapping("/user/{userId}/names")
  public ResponseEntity<List<String>> getListNamesByUser(@PathVariable UUID userId) {
    List<String> listNames = trainingListService.getDistinctListNamesByUserId(userId);
    return new ResponseEntity<>(listNames, HttpStatus.OK);
  }

  @GetMapping("/user/{userId}/list/{listName}")
  public ResponseEntity<List<TrainingList>> getExercisesByListName(
      @PathVariable UUID userId, @PathVariable String listName) {
    List<TrainingList> exercises = trainingListService.getExercisesByListName(userId, listName);
    return new ResponseEntity<>(exercises, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getTrainingListById(@PathVariable Long id) {
    Optional<TrainingList> trainingList = trainingListService.findTrainingListById(id);
    if (trainingList.isPresent()) {
      return new ResponseEntity<>(trainingList.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        Map.of("message", "Liste d'entraînement non trouvée"), HttpStatus.NOT_FOUND);
  }

  @PostMapping("/user/{userId}/exercises")
  public ResponseEntity<?> addExerciseToTrainingList(
      @PathVariable UUID userId, @Valid @RequestBody AddExerciseToTrainingListRequest request) {
    try {
      TrainingList trainingList =
          trainingListService.addExerciseToTrainingList(
              userId, request.getExercice_id(), request.getName(), request.getDescription());
      return new ResponseEntity<>(trainingList, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTrainingList(
      @PathVariable Long id, @Valid @RequestBody TrainingList trainingList) {
    Optional<TrainingList> updated = trainingListService.updateTrainingList(id, trainingList);
    if (updated.isPresent()) {
      return new ResponseEntity<>(updated.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        Map.of("message", "Liste d'entraînement non trouvée"), HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTrainingList(@PathVariable Long id) {
    boolean deleted = trainingListService.deleteTrainingList(id);
    if (deleted) {
      return new ResponseEntity<>(
          Map.of("message", "Liste d'entraînement supprimée"), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        Map.of("message", "Liste d'entraînement non trouvée"), HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/user/{userId}/list/{listName}/exercise/{exerciceId}")
  public ResponseEntity<?> removeExerciseFromList(
      @PathVariable UUID userId, @PathVariable String listName, @PathVariable Long exerciceId) {
    boolean deleted = trainingListService.removeExerciseFromList(userId, listName, exerciceId);
    if (deleted) {
      return new ResponseEntity<>(Map.of("message", "Exercice retiré de la liste"), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        Map.of("message", "Exercice non trouvé dans cette liste"), HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/user/{userId}/list/{listName}")
  public ResponseEntity<?> deleteEntireList(
      @PathVariable UUID userId, @PathVariable String listName) {
    boolean deleted = trainingListService.deleteEntireList(userId, listName);
    if (deleted) {
      return new ResponseEntity<>(Map.of("message", "Liste supprimée"), HttpStatus.OK);
    }
    return new ResponseEntity<>(Map.of("message", "Liste non trouvée"), HttpStatus.NOT_FOUND);
  }
}
