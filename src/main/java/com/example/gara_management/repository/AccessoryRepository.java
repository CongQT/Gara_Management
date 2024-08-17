package com.example.gara_management.repository;

import com.example.gara_management.entity.Accessory;
import com.example.gara_management.entity.Services;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface AccessoryRepository extends JpaRepositoryImplementation<Accessory, Integer> {

}