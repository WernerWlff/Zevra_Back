package com.zevra.zevra.repositories;

import com.zevra.zevra.entities.Training_list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface training_listRepository extends JpaRepository<Training_list, Long> {}
