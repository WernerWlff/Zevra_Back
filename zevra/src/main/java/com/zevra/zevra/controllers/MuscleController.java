package com.zevra.zevra.controllers;

import com.zevra.zevra.entities.Muscle;
import com.zevra.zevra.services.MuscleService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/muscles")
public class MuscleController {

  private final MuscleService muscleService;

  public MuscleController(MuscleService muscleService) {
    this.muscleService = muscleService;
  }

  @GetMapping
  public ResponseEntity<List<Muscle>> getAllMuscles() {
    return new ResponseEntity<>(muscleService.getAllMuscles(), HttpStatus.OK);
  }
}
