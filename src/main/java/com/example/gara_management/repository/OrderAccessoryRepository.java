package com.example.gara_management.repository;

import com.example.gara_management.entity.Order;
import com.example.gara_management.entity.OrderAccessory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface OrderAccessoryRepository extends JpaRepositoryImplementation<OrderAccessory, Integer> {

}