package com.zevra.zevra.services;

import com.zevra.zevra.entities.Type;
import com.zevra.zevra.repositories.TypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
  private final TypeRepository typeRepository;

  public TypeService(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  public List<Type> getAllTypes() {
    return this.typeRepository.findAll();
  }
}
