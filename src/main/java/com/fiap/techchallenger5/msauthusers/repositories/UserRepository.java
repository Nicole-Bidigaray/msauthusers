package com.fiap.techchallenger5.msauthusers.repositories;

import com.fiap.techchallenger5.msauthusers.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
