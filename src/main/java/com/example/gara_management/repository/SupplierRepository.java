package com.example.gara_management.repository;

import com.example.gara_management.entity.Accessory;
import com.example.gara_management.entity.Supplier;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface SupplierRepository extends JpaRepositoryImplementation<Supplier, Integer> {

}