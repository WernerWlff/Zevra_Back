package com.zevra.zevra.controllers;

import com.zevra.zevra.entities.Type;
import com.zevra.zevra.services.TypeService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/types")
public class TypeController {

  private final TypeService typeService;

  public TypeController(TypeService typeService) {
    this.typeService = typeService;
  }

  @GetMapping
  public ResponseEntity<List<Type>> getAllTypes() {
    return new ResponseEntity<>(typeService.getAllTypes(), HttpStatus.OK);
  }
}
