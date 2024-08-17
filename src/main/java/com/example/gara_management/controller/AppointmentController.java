package com.example.gara_management.controller;

import com.example.gara_management.enumtype.AppointmentStatus;
import com.example.gara_management.payload.request.AppointmentRequest;
import com.example.gara_management.payload.response.AppointmentResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create_appointment")
    public BaseResponse<AppointmentResponse> createAppointment(@RequestBody @Valid AppointmentRequest request) {
        return BaseResponse.of(appointmentService.createAppointment(request));
    }

    @GetMapping("/get_appointment")
    public BaseResponse<AppointmentResponse> getAppointment(@RequestParam(name = "id") Integer id) {
        return BaseResponse.of(appointmentService.getAppointment(id));
    }

    @PostMapping("/update_status")
    public BaseResponse<Object> updateStatus(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "status") AppointmentStatus status
    ) {
        appointmentService.updateStatus(id, status);
        return BaseResponse.of(Collections.emptyMap());
    }

    @GetMapping("/get_all_by_client")
    public BaseResponse<PageResponse<AppointmentResponse>> getAllByClient(
            Pageable pageable
    ) {
        return BaseResponse.of(
                appointmentService.getAllByClient(pageable));
    }

    @GetMapping("/list_or_search")
    public BaseResponse<PageResponse<AppointmentResponse>> listOrSearch(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) AppointmentStatus status,
            Pageable pageable
    ) {
        return BaseResponse.of(
                appointmentService.listOrSearch(userId, status, pageable));
    }
}
