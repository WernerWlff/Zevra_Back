package com.zevra.zevra.controllers;

import com.zevra.zevra.dto.*;
import com.zevra.zevra.services.AuthService;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
    try {
      RegisterResponse response = authService.register(request);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
    try {
      LoginResponse response = authService.login(request);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
  }

  @PutMapping("/{id}/password")
  public ResponseEntity<?> updatePassword(
      @PathVariable UUID id, @Valid @RequestBody UpdatePasswordRequest request) {
    try {
      authService.updatePassword(id, request);
      return new ResponseEntity<>(Map.of("message", "mot de passe mis à jour"), HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
