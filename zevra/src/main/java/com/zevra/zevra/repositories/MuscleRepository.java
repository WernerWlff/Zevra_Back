package com.zevra.zevra.repositories;

import com.zevra.zevra.entities.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
}
