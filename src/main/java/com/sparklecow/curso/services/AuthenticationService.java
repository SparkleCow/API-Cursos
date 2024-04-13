package com.sparklecow.curso.services;

import com.sparklecow.curso.entities.user.Role;
import com.sparklecow.curso.entities.user.User;
import com.sparklecow.curso.entities.user.dto.UserRequestDto;
import com.sparklecow.curso.repositories.RoleRepository;
import com.sparklecow.curso.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRequestDto userRequest) {
        Role role = roleRepository.findByName("USER").orElseThrow(
                () -> new RuntimeException("Role not found")
        );
        User user = User.builder()
                .firstName(userRequest.firstName())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .roles(List.of(role))
                .accountLocked(false)
                .enabled(true)
                .build();
        userRepository.save(user);
    }
}
