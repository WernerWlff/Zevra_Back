package com.zevra.zevra.services;

import com.zevra.zevra.dto.CreateExerciceRequest;
import com.zevra.zevra.dto.UpdateExerciceRequest;
import com.zevra.zevra.entities.Exercice;
import com.zevra.zevra.entities.Muscle;
import com.zevra.zevra.entities.Type;
import com.zevra.zevra.repositories.ExerciceRepository;
import com.zevra.zevra.repositories.MuscleRepository;
import com.zevra.zevra.repositories.TypeRepository;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ExerciceService {
  private final ExerciceRepository exerciceRepository;
  private final TypeRepository typeRepository;
  private final MuscleRepository muscleRepository;

  public ExerciceService(
      ExerciceRepository exerciceRepository,
      TypeRepository typeRepository,
      MuscleRepository muscleRepository) {
    this.exerciceRepository = exerciceRepository;
    this.typeRepository = typeRepository;
    this.muscleRepository = muscleRepository;
  }

  public List<Exercice> getAllExercices() {
    return exerciceRepository.findAll();
  }

  public Optional<Exercice> getExerciceById(Long id) {
    return exerciceRepository.findById(id);
  }

  @Transactional
  public Exercice createExercice(CreateExerciceRequest request) {
    Type type =
        typeRepository
            .findById(request.getType_id())
            .orElseThrow(() -> new RuntimeException("Type non trouvé"));
    Muscle muscle =
        muscleRepository
            .findById(request.getMuscle_id())
            .orElseThrow(() -> new RuntimeException("Muscle non trouvé"));

    Exercice exercice = new Exercice();
    exercice.setType(type);
    exercice.setMuscle(muscle);
    exercice.setDuration(request.getDuration());

    if (request.getBeginner_rep() != null) {
      exercice.setBeginner_rep(request.getBeginner_rep());
    }

    if (request.getIntermediate_rep() != null) {
      exercice.setIntermediate_rep(request.getIntermediate_rep());
    }

    if (request.getHard_rep() != null) {
      exercice.setHard_rep(request.getHard_rep());
    }

    Date now = new Date(System.currentTimeMillis());
    exercice.setCreated_at(now);
    exercice.setUpdated_at(now);

    return exerciceRepository.save(exercice);
  }

  @Transactional
  public Optional<Exercice> updateExercice(Long id, UpdateExerciceRequest request) {
    Optional<Exercice> optionalExercice = exerciceRepository.findById(id);

    if (optionalExercice.isPresent()) {
      Exercice existingExercice = optionalExercice.get();

      if (request.getType_id() != null) {
        Type type =
            typeRepository
                .findById(request.getType_id())
                .orElseThrow(() -> new RuntimeException("Type non trouvé"));
        existingExercice.setType(type);
      }

      if (request.getMuscle_id() != null) {
        Muscle muscle =
            muscleRepository
                .findById(request.getMuscle_id())
                .orElseThrow(() -> new RuntimeException("Muscle non trouvé"));
        existingExercice.setMuscle(muscle);
      }

      if (request.getDuration() != null) {
        existingExercice.setDuration(request.getDuration());
      }

      if (request.getBeginner_rep() != null) {
        existingExercice.setBeginner_rep(request.getBeginner_rep());
      }

      if (request.getIntermediate_rep() != null) {
        existingExercice.setIntermediate_rep(request.getIntermediate_rep());
      }

      if (request.getHard_rep() != null) {
        existingExercice.setHard_rep(request.getHard_rep());
      }

      existingExercice.setUpdated_at(new Date(System.currentTimeMillis()));

      return Optional.of(exerciceRepository.save(existingExercice));
    }

    return Optional.empty();
  }

  @Transactional
  public Optional<Exercice> deleteExercice(Long id) {
    Optional<Exercice> exercice = exerciceRepository.findById(id);
    if (exercice.isPresent()) {
      exerciceRepository.deleteById(id);
    }
    return exercice;
  }
}
