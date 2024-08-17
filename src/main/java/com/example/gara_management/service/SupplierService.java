package com.example.gara_management.service;

import com.example.gara_management.entity.Supplier;
import com.example.gara_management.payload.request.SupplierRequest;
import com.example.gara_management.payload.response.SupplierResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import org.springframework.data.domain.Pageable;


public interface SupplierService extends BaseEntityService<Supplier, Integer>{

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse updateSupplier(Integer id, SupplierRequest request);

    SupplierResponse getSupplier(Integer id);

    void delete(Integer id);

    PageResponse<SupplierResponse> listOrSearch(String name, Pageable pageable);
}
