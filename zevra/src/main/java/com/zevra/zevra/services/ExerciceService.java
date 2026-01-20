package com.zevra.zevra.services;

import com.zevra.zevra.entities.Exercice;
import com.zevra.zevra.repositories.ExerciceRepository;
import com.zevra.zevra.repositories.MuscleRepository;
import com.zevra.zevra.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciceService {
    private final ExerciceRepository exerciceRepository;
    private final TypeRepository typeRepository;
    private final MuscleRepository muscleRepository;

    public ExerciceService(ExerciceRepository exerciceRepository,  TypeRepository typeRepository, MuscleRepository muscleRepository) {
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
}
