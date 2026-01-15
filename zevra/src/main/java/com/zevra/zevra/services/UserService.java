package com.zevra.zevra.services;

import com.zevra.zevra.dto.UpdateUserRequest;
import com.zevra.zevra.entities.User;
import com.zevra.zevra.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public Optional<User>updateUser(UUID id, UpdateUserRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (request.getFirstName() != null) {
                existingUser.setFirstname(request.getFirstName());
            }

            if (request.getLastName() != null) {
                existingUser.setLastname(request.getLastName());
            }

            if (request.getUsername() != null) {
                if (userRepository.existsByUsername(request.getUsername()) &&  !existingUser.getUsername().equals(request.getUsername())){
                    throw new RuntimeException("Ce nom d'utilisateur est déjà utilisé");
                }
                existingUser.setUsername(request.getUsername());
            }

            if (request.getEmail() != null) {
                if (userRepository.existsByEmail(request.getEmail()) && !existingUser.getEmail().equals(request.getEmail())){
                    throw new RuntimeException("Cet Email est déjà utilisé");
                }
                existingUser.setEmail(request.getEmail());
            }

            existingUser.setUpdated_at(new Date());
            User savedUser = userRepository.save(existingUser);
            return Optional.of(savedUser);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<User>deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        }
        return user;
    }
}
