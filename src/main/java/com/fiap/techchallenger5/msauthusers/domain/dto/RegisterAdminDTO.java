package com.fiap.techchallenger5.msauthusers.domain.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fiap.techchallenger5.msauthusers.domain.entities.User;
import com.fiap.techchallenger5.msauthusers.domain.entities.UserRole;

public record RegisterAdminDTO (
        String name,
        String email,
        String password
) {

	public User toEntity(){
		return new User(name, email, password, UserRole.ADMIN);
	}
	public User toEntityWithBCryptEncoder(){
		return new User(name, email, new BCryptPasswordEncoder().encode(password), UserRole.ADMIN);
	}
}
