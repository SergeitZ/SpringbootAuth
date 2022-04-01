package com.springauth.authapp.repositories;

import com.springauth.authapp.models.auth.ERole;
import com.springauth.authapp.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
