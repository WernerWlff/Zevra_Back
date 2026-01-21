package com.zevra.zevra.services;

import com.zevra.zevra.entities.Exercice;
import com.zevra.zevra.entities.Favorite;
import com.zevra.zevra.entities.User;
import com.zevra.zevra.repositories.ExerciceRepository;
import com.zevra.zevra.repositories.FavoriteRepository;
import com.zevra.zevra.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
  private final FavoriteRepository favoriteRepository;
  private final UserRepository userRepository;
  private final ExerciceRepository exerciceRepository;

  public FavoriteService(
      FavoriteRepository favoriteRepository,
      UserRepository userRepository,
      ExerciceRepository exerciceRepository) {
    this.favoriteRepository = favoriteRepository;
    this.userRepository = userRepository;
    this.exerciceRepository = exerciceRepository;
  }

  public List<Favorite> getAllFavorites() {
    return favoriteRepository.findAll();
  }

  public List<Favorite> getFavoriteByUserId(UUID userId) {
    return favoriteRepository.findByUserId(userId);
  }

  public Optional<Favorite> getFavoriteById(Long id) {
    return favoriteRepository.findById(id);
  }

  @Transactional
  public Favorite addExerciceToFavorite(
      UUID userId, Long exerciceId, String name, String description) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    Exercice exercice =
        exerciceRepository
            .findById(exerciceId)
            .orElseThrow(() -> new RuntimeException("Exercice non trouvé"));

    List<Favorite> existingFavorites =
        favoriteRepository.findByUserIdAndExerciceId(userId, exerciceId);
    if (!existingFavorites.isEmpty()) {
      throw new RuntimeException("Cet exercice est déjà dans vos favoris");
    }

    Favorite favorite = new Favorite();
    favorite.setUser(user);
    favorite.setExercice(exercice);
    favorite.setName(name);
    favorite.setDescription(description);

    Date now = new Date();
    favorite.setCreated_at(now);
    favorite.setUpdated_at(now);

    return favoriteRepository.save(favorite);
  }

  @Transactional
  public Optional<Favorite> deleteFavorite(Long id) {
    Optional<Favorite> favorite = favoriteRepository.findById(id);
    if (favorite.isPresent()) {
      favoriteRepository.deleteById(id);
    }
    return favorite;
  }

  @Transactional
  public boolean removeExerciceFromFavorite(UUID userId, Long exerciceId) {
    List<Favorite> favorites = favoriteRepository.findByUserIdAndExerciceId(userId, exerciceId);
    if (!favorites.isEmpty()) {
      favoriteRepository.deleteAll(favorites);
      return true;
    }
    return false;
  }
}
