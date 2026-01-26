package com.zevra.zevra.services;

import com.zevra.zevra.entities.Muscle;
import com.zevra.zevra.repositories.MuscleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MuscleService {
  private final MuscleRepository muscleRepository;

  public MuscleService(MuscleRepository muscleRepository) {
    this.muscleRepository = muscleRepository;
  }

  public List<Muscle> getAllMuscles() {
    return muscleRepository.findAll();
  }
}
