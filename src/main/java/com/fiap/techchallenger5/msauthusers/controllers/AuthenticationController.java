package com.fiap.techchallenger5.msauthusers.controllers;

import com.fiap.techchallenger5.msauthusers.domain.dto.AuthenticationDTO;
import com.fiap.techchallenger5.msauthusers.domain.dto.LoginResponseDTO;
import com.fiap.techchallenger5.msauthusers.domain.dto.RegisterAdminDTO;
import com.fiap.techchallenger5.msauthusers.domain.dto.RegisterUserDTO;
import com.fiap.techchallenger5.msauthusers.domain.entities.User;
import com.fiap.techchallenger5.msauthusers.infra.security.TokenService;
import com.fiap.techchallenger5.msauthusers.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        var idUsuario = userRepository.findByEmail(data.email()).getId();
        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(idUsuario, token));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid RegisterAdminDTO data) {
        if(this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

        this.userRepository.save(data.toEntityWithBCryptEncoder());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserDTO data) {
        if(this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

        this.userRepository.save(data.toEntityWithBCryptEncoder());

        return ResponseEntity.ok().build();
    }
}
