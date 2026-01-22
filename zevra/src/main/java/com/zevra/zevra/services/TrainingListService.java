package com.zevra.zevra.services;

import com.zevra.zevra.entities.Exercice;
import com.zevra.zevra.entities.TrainingList;
import com.zevra.zevra.entities.User;
import com.zevra.zevra.repositories.ExerciceRepository;
import com.zevra.zevra.repositories.TrainingListRepository;
import com.zevra.zevra.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TrainingListService {
  private final TrainingListRepository trainingListRepository;
  private final UserRepository userRepository;
  private final ExerciceRepository exerciceRepository;

  public TrainingListService(
      TrainingListRepository trainingListRepository,
      UserRepository userRepository,
      ExerciceRepository exerciceRepository) {
    this.trainingListRepository = trainingListRepository;
    this.userRepository = userRepository;
    this.exerciceRepository = exerciceRepository;
  }

  public List<TrainingList> findAllTrainingList() {
    return trainingListRepository.findAll();
  }

  public List<TrainingList> findTrainingListByUserId(UUID userId) {
    return trainingListRepository.findByUserId(userId);
  }

  public List<TrainingList> getExercicesByListName(UUID userId, String listName) {
    return trainingListRepository.findByUserIdAndName(userId, listName);
  }

  public List<String> getDistinctListNamesByUserId(UUID userId) {
    List<TrainingList> allLists = trainingListRepository.findByUserId(userId);
    return allLists.stream().map(TrainingList::getName).distinct().collect(Collectors.toList());
  }

  public Optional<TrainingList> findTrainingListById(Long id) {
    return trainingListRepository.findById(id);
  }

  @Transactional
  public TrainingList createTrainingList(TrainingList trainingList) {
    return trainingListRepository.save(trainingList);
  }

  @Transactional
  public TrainingList addExerciceToTrainingList(
      UUID userId, Long exerciceId, String name, String description) {

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    Exercice exercice =
        exerciceRepository
            .findById(exerciceId)
            .orElseThrow(() -> new RuntimeException("Exercice non trouvé"));

    List<TrainingList> existingEntries = trainingListRepository.findByUserIdAndName(userId, name);
    boolean exerciceAlreadyInList =
        existingEntries.stream().anyMatch(tl -> tl.getExercice().getId().equals(exerciceId));

    if (exerciceAlreadyInList) {
      throw new RuntimeException("Cet exercice est déjà présent dans cette liste");
    }

    TrainingList trainingList = new TrainingList();
    trainingList.setUser(user);
    trainingList.setExercice(exercice);
    trainingList.setName(name);
    trainingList.setDescription(description);

    Date now = new Date();
    trainingList.setCreated_at(now);
    trainingList.setUpdated_at(now);

    return trainingListRepository.save(trainingList);
  }

  @Transactional
  public Optional<TrainingList> updateTrainingList(Long id, TrainingList trainingList) {
    Optional<TrainingList> optionalTrainingList = trainingListRepository.findById(id);

    if (optionalTrainingList.isPresent()) {
      TrainingList existingTrainingList = optionalTrainingList.get();
      existingTrainingList.setName(trainingList.getName());
      existingTrainingList.setDescription(trainingList.getDescription());
      existingTrainingList.setUpdated_at(new Date());
      return Optional.of(trainingListRepository.save(existingTrainingList));
    }
    return Optional.empty();
  }

  @Transactional
  public boolean deleteTrainingList(Long id) {
    if (trainingListRepository.existsById(id)) {
      trainingListRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Transactional
  public boolean removeExerciceFromList(UUID userId, String listName, Long exerciceId) {
    List<TrainingList> listEntries = trainingListRepository.findByUserIdAndName(userId, listName);
    Optional<TrainingList> toDelete =
        listEntries.stream().filter(tl -> tl.getExercice().getId().equals(exerciceId)).findFirst();

    if (toDelete.isPresent()) {
      trainingListRepository.delete(toDelete.get());
      return true;
    }
    return false;
  }

  @Transactional
  public boolean deleteEntireList(UUID userId, String listName) {
    List<TrainingList> listEntries = trainingListRepository.findByUserIdAndName(userId, listName);
    if (!listEntries.isEmpty()) {
      trainingListRepository.deleteAll(listEntries);
      return true;
    }
    return false;
  }
}
