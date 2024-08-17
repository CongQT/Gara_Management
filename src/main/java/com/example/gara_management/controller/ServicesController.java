package com.example.gara_management.controller;

import com.example.gara_management.constant.RoleCode;
import com.example.gara_management.payload.request.ChangePasswordRequest;
import com.example.gara_management.payload.request.RegistrationRequest;
import com.example.gara_management.payload.request.ServiceRequest;
import com.example.gara_management.payload.request.UserInfoRequest;
import com.example.gara_management.payload.response.ServiceResponse;
import com.example.gara_management.payload.response.UserInfoResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.service.ServicesService;
import com.example.gara_management.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping("/create_service")
    public BaseResponse<ServiceResponse> createService(@RequestBody @Valid ServiceRequest request) {
        return BaseResponse.of(servicesService.createService(request));
    }

    @PostMapping("/update_service")
    public BaseResponse<ServiceResponse> updateService(
            @RequestParam(name = "id") Integer id,
            @RequestBody @Valid ServiceRequest request) {
        return BaseResponse.of(servicesService.updateService(id, request));
    }

    @GetMapping("/get_service")
    public BaseResponse<ServiceResponse> getService(@RequestParam(name = "id") Integer id) {
        return BaseResponse.of(servicesService.getService(id));
    }

    @GetMapping("/list_or_search")
    public BaseResponse<PageResponse<ServiceResponse>> listOrSearch(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return BaseResponse.of(
                servicesService.listOrSearch(name, pageable));
    }
}
