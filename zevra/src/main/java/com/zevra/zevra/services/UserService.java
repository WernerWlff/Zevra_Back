package com.zevra.zevra.services;

import com.zevra.zevra.entities.User;
import com.zevra.zevra.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public Optional<User>updateUser(UUID id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
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
