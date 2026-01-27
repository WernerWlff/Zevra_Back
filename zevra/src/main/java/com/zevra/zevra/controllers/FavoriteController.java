package com.zevra.zevra.controllers;

import com.zevra.zevra.dto.AddExerciceToFavoriteRequest;
import com.zevra.zevra.dto.FavoriteResponse;
import com.zevra.zevra.entities.Favorite;
import com.zevra.zevra.services.FavoriteService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
  private final FavoriteService favoriteService;

  public FavoriteController(FavoriteService favoriteService) {
    this.favoriteService = favoriteService;
  }

  @GetMapping
  public ResponseEntity<List<FavoriteResponse>> getAllFavorites() {
    return new ResponseEntity<>(favoriteService.getAllFavorites(), HttpStatus.OK);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<FavoriteResponse>> getFavoritesByUser(@PathVariable UUID userId) {
    return new ResponseEntity<>(favoriteService.getFavoriteByUserId(userId), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FavoriteResponse> getFavoriteById(@PathVariable Long id) {
    Optional<FavoriteResponse> favorite = favoriteService.getFavoriteById(id);
    if (favorite.isPresent()) {
      return new ResponseEntity<>(favorite.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/user/{userId}/exercices")
  public ResponseEntity<?> addExerciceToFavorite(
      @PathVariable UUID userId, @Valid @RequestBody AddExerciceToFavoriteRequest request) {
    try {
      FavoriteResponse favorite =
          favoriteService.addExerciceToFavorite(
              userId, request.getExercice_id(), request.getName(), request.getDescription());
      return new ResponseEntity<>(favorite, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
    Optional<Favorite> deletedFavorite = favoriteService.deleteFavorite(id);

    if (deletedFavorite.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/user/{userId}/exercice/{exerciceId}")
  public ResponseEntity<?> removeExerciceFromFavorite(
      @PathVariable UUID userId, @PathVariable Long exerciceId) {
    boolean deleted = favoriteService.removeExerciceFromFavorite(userId, exerciceId);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
