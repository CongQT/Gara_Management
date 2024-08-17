package com.example.gara_management.service;

import com.example.gara_management.entity.Accessory;
import com.example.gara_management.entity.Services;
import com.example.gara_management.payload.request.AccessoryRequest;
import com.example.gara_management.payload.request.ServiceRequest;
import com.example.gara_management.payload.response.AccessoryResponse;
import com.example.gara_management.payload.response.ServiceResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import org.springframework.data.domain.Pageable;


public interface AccessoryService extends BaseEntityService<Accessory, Integer>{

    AccessoryResponse createAccessory(AccessoryRequest request);

    AccessoryResponse updateAccessory(Integer id, AccessoryRequest request);

    AccessoryResponse getAccessory(Integer id);

    void delete(Integer id);

    PageResponse<AccessoryResponse> listOrSearch(String name, Pageable pageable);
}
