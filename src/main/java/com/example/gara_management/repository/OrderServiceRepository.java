package com.example.gara_management.repository;

import com.example.gara_management.entity.OrderServices;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface OrderServiceRepository extends JpaRepositoryImplementation<OrderServices, Integer> {

}