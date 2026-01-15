package com.zevra.zevra.services;

import com.zevra.zevra.dto.RegisterRequest;
import com.zevra.zevra.dto.RegisterResponse;
import com.zevra.zevra.dto.UpdatePasswordRequest;
import com.zevra.zevra.entities.Role;
import com.zevra.zevra.entities.User;
import com.zevra.zevra.repositories.RoleRepository;
import com.zevra.zevra.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Le nom d'utilisateur est déjà utilisé");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("L'email est déjà utilisé");
        }

        Long roleId = request.getRoleId() != null ? request.getRoleId() : 1L;
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        
        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Le rôle spécifié n'existe pas");
        }

        Role role = roleOptional.get();

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        user.setRole(role);

        Date now = new Date();
        user.setCreated_at(now);
        user.setUpdated_at(now);

        User savedUser = userRepository.save(user);
        
        RegisterResponse response = new RegisterResponse();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setFirstname(savedUser.getFirstname());
        response.setLastname(savedUser.getLastname());
        response.setMessage("Inscription réussie");

        return response;
    }

    @Transactional
    public void updatePassword(UUID userId, UpdatePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Le mot de passe actuel est incorrect");
        }

        String hashedPassword = passwordEncoder.encode(request.getNewPassword());

        user.setPassword(hashedPassword);
        user.setUpdated_at(new Date());

        userRepository.save(user);
    }
}
