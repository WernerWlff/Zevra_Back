package com.zevra.zevra.repositories;

import com.zevra.zevra.entities.TrainingList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingListRepository extends JpaRepository<TrainingList, Long> {
  // get all the lists from an user
  List<TrainingList> findByUserId(UUID id);

  // manage exercices in a list
  List<TrainingList> findByUserIdAndName(UUID id, String name);

  // display all lists from an user
  List<TrainingList> findDistinctByUserId(UUID id);
}
