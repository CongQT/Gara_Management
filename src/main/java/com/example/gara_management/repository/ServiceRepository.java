package com.example.gara_management.repository;

import com.example.gara_management.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ServiceRepository extends JpaRepositoryImplementation<Services, Integer> {

}