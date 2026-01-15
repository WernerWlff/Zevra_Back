package com.zevra.zevra.repositories;

import com.zevra.zevra.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
