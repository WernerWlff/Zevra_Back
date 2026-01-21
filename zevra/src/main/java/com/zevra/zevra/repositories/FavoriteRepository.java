package com.zevra.zevra.repositories;

import com.zevra.zevra.entities.Favorite;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

  List<Favorite> findByUserId(UUID userId);

  List<Favorite> findByUserIdAndExerciceId(UUID userId, Long exerciceId);
}
