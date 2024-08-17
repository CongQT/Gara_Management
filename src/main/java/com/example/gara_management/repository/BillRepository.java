package com.example.gara_management.repository;

import com.example.gara_management.entity.Bill;
import com.example.gara_management.entity.Order;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface BillRepository extends JpaRepositoryImplementation<Bill, Integer> {

}