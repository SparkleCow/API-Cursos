package com.sparklecow.curso.services;

import com.sparklecow.curso.config.jwt.JwtUtils;
import com.sparklecow.curso.entities.user.Role;
import com.sparklecow.curso.entities.user.Token;
import com.sparklecow.curso.entities.user.User;
import com.sparklecow.curso.entities.user.dto.AuthenticationRequestDto;
import com.sparklecow.curso.entities.user.dto.AuthenticationResponseDto;
import com.sparklecow.curso.entities.user.dto.UserRequestDto;
import com.sparklecow.curso.repositories.RoleRepository;
import com.sparklecow.curso.repositories.TokenRepository;
import com.sparklecow.curso.repositories.UserRepository;
import com.sparklecow.curso.services.email.EmailService;
import com.sparklecow.curso.services.email.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Servicio para autenticar, registrar y validar usuarios*/
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(UserRequestDto userRequest) throws MessagingException {
        Role role = roleRepository.findByName("USER").orElseThrow(
                () -> new RuntimeException("Role not found")
        );
        User user = User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .roles(List.of(role))
                .accountLocked(false)
                .enabled(false)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        String token = generateAndSaveToken(user);
        emailService.sendEmail(user.getEmail(), user.getFullName(), EmailTemplate.ACTIVATE_ACCOUNT,
            activationUrl, token, "Activate account");
    }

    private String generateAndSaveToken(User user) {
        String generatedToken = generateToken(6);
        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(10))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    public String generateToken(int length) {
        String token = "1234567890";
        SecureRandom random = new SecureRandom();
        StringBuilder tokenSb = new StringBuilder();
        int indexRandom;
        for(int i=0;i<length;i++){
            indexRandom = random.nextInt(token.length());
            tokenSb.append(token.charAt(indexRandom));
        }
        return tokenSb.toString();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.email(), authenticationRequestDto.password()));
        User user = userRepository.findByEmail(authenticationRequestDto.email()).orElseThrow(RuntimeException::new);
        Map<String, Object> claims = new HashMap<>();
        claims.put("fullname", user.getFullName());
        String token = jwtUtils.generateToken(claims, user);
        return new AuthenticationResponseDto(token);
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token tokenResult = tokenRepository.findByToken(token).orElseThrow(RuntimeException::new);
        if(LocalDateTime.now().isAfter(tokenResult.getExpiredAt())){
            sendValidationEmail(tokenResult.getUser());
            throw new RuntimeException("Token expired");
        }
        User user = tokenResult.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        tokenResult.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(tokenResult);
    }
}
