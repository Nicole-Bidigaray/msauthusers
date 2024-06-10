package com.fiap.techchallenger5.msauthusers.domain.dto;

import com.fiap.techchallenger5.msauthusers.domain.entities.UserRole;

public record RegisterDTO (
        String name,
        String email,
        String password,
        UserRole role
) {
}
