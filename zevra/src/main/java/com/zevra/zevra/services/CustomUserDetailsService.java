package com.zevra.zevra.services;

import com.zevra.zevra.entities.CustomUserDetails;
import com.zevra.zevra.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import com.zevra.zevra.entities.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur inconnu avec l'email : " + email);
        }

        Collection<GrantedAuthority> authorities = getAuthorities(user);

        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if(user.getRole() != null && user.getRole().getPermission() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getPermission()));
        }

        return authorities;
    }
}
