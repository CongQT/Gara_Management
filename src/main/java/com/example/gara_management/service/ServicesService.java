package com.example.gara_management.service;

import com.example.gara_management.entity.Services;
import com.example.gara_management.payload.request.ServiceRequest;
import com.example.gara_management.payload.response.ServiceResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import org.springframework.data.domain.Pageable;


public interface ServicesService extends BaseEntityService<Services, Integer>{

    ServiceResponse createService(ServiceRequest request);

    ServiceResponse updateService(Integer id, ServiceRequest request);

    ServiceResponse getService(Integer id);

    void delete(Integer id);

    PageResponse<ServiceResponse> listOrSearch(String name, Pageable pageable);
}
