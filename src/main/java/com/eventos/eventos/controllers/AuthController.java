package com.eventos.eventos.controllers;

import com.eventos.eventos.domain.user.User;
import com.eventos.eventos.dto.LoginRequestDTO;
import com.eventos.eventos.dto.RegisterRequestDTO;

import com.eventos.eventos.dto.ResponseRegisterUserSucessDTO;
import com.eventos.eventos.infra.security.TokenService;
import com.eventos.eventos.repositories.UserRepository;
import com.eventos.eventos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthService service;

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginRequestDTO body){
////        User user = this.repository.findByEmail(body.email()).orElseThrow(()-> new RuntimeException("USUARIO NAO EXISTE"));
////        if(passwordEncoder.matches(body.password(), user.getPassword())){
////            String token = this.tokenService.generateToken(user);
////            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
////        }
////        return ResponseEntity.badRequest().build();
//    }

    @PostMapping("/register")
    public ResponseRegisterUserSucessDTO register(@RequestBody RegisterRequestDTO body){
        return service.registerNewUser(body);
    }
}
