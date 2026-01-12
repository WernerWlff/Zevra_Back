package com.zevra.zevra.repositories;

import com.zevra.zevra.entities.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
}
