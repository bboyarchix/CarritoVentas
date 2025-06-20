package com.example.microservice_auth.repository;

import com.example.microservice_auth.entity.ERole;
import com.example.microservice_auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role , Long> {
    Optional<Role> findByName(ERole name);
}
