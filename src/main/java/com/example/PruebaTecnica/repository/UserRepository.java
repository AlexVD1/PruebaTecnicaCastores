package com.example.PruebaTecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PruebaTecnica.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
