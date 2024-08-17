package com.example.gara_management.repository;

import com.example.gara_management.entity.Accessory;
import com.example.gara_management.entity.Appointment;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface AppointmentRepository extends JpaRepositoryImplementation<Appointment, Integer> {

}