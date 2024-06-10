package com.fiap.techchallenger5.msauthusers.repositories;

import com.fiap.techchallenger5.msauthusers.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
}
