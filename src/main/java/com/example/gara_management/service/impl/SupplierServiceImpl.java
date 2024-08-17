package com.example.gara_management.service.impl;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.Supplier;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.filter.SupplierFilter;
import com.example.gara_management.payload.filter.BaseEntityFilter;
import com.example.gara_management.payload.request.SupplierRequest;
import com.example.gara_management.payload.response.SupplierResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.repository.SupplierRepository;
import com.example.gara_management.service.SupplierService;
import com.example.gara_management.util.MyStringUtils;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepo;

    private final ModelMapper modelMapper;

    @Override
    public JpaRepository<Supplier, Integer> getRepository() {
        return supplierRepo;
    }

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {
        Supplier supplier = Supplier.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
        supplierRepo.save(supplier);
        return modelMapper.map(supplier, SupplierResponse.class);
    }

    @Override
    public SupplierResponse updateSupplier(Integer id, SupplierRequest request) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found supplier with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        modelMapper.map(request, supplier);
        supplierRepo.save(supplier);
        return modelMapper.map(supplier, SupplierResponse.class);
    }

    @Override
    public SupplierResponse getSupplier(Integer id) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found supplier with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        return modelMapper.map(supplier, SupplierResponse.class);
    }

    @Override
    public void delete(Integer id) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found service with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        supplierRepo.delete(supplier);
    }

    @Override
    public PageResponse<SupplierResponse> listOrSearch(String name, Pageable pageable) {
        BaseEntityFilter<SupplierFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        if (StringUtils.isNotBlank(name)) {
            filter.getFilters().add(SupplierFilter.builder()
                    .nameLk(MyStringUtils.buildSqlLikePattern(name))
                    .build());
        }
        return PageResponse
                .toResponse(filter(filter), supplier -> modelMapper.map(supplier, SupplierResponse.class));
    }
    
}