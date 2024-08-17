package com.example.gara_management.repository;

import com.example.gara_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository
    extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
  Optional<User> findFirstByUsername(String username);

}