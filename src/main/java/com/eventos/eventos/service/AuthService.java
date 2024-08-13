package com.eventos.eventos.service;

import com.eventos.eventos.domain.user.User;
import com.eventos.eventos.dto.LoginRequestDTO;
import com.eventos.eventos.dto.RegisterRequestDTO;

import com.eventos.eventos.dto.ResponseRegisterUserSucessDTO;
import com.eventos.eventos.exceptions.UserExistException;
import com.eventos.eventos.infra.security.TokenService;
import com.eventos.eventos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseRegisterUserSucessDTO registerNewUser(RegisterRequestDTO body){
        Optional<User> user = repository.findByEmail(body.email());

        if(user.isPresent()){
            try {
                throw new Exception("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(passwordEncoder.encode(body.password()));
        repository.save(newUser);

        String token = tokenService.generateToken(newUser);

        return new ResponseRegisterUserSucessDTO(newUser.getName(), token);
    }
}
