package com.example.gara_management.service;

import com.example.gara_management.entity.Appointment;
import com.example.gara_management.entity.Appointment;
import com.example.gara_management.enumtype.AppointmentStatus;
import com.example.gara_management.payload.request.AppointmentRequest;
import com.example.gara_management.payload.response.AppointmentResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import org.springframework.data.domain.Pageable;


public interface AppointmentService extends BaseEntityService<Appointment, Integer>{

    AppointmentResponse createAppointment(AppointmentRequest request);

    AppointmentResponse getAppointment(Integer id);

    void updateStatus(Integer id, AppointmentStatus status);

    PageResponse<AppointmentResponse> getAllByClient(Pageable pageable);

    PageResponse<AppointmentResponse> listOrSearch(Integer userId, AppointmentStatus status, Pageable pageable);
}
