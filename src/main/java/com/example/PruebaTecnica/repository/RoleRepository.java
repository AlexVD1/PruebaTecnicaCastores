package com.example.PruebaTecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PruebaTecnica.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
