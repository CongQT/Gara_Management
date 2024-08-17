package com.example.gara_management.service.impl;

import com.example.gara_management.config.properties.JwtProperties;
import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.Services;
import com.example.gara_management.entity.User;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.filter.BaseEntityFilter;
import com.example.gara_management.payload.filter.ServicesFilter;
import com.example.gara_management.payload.request.ServiceRequest;
import com.example.gara_management.payload.response.ServiceResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.repository.ServiceRepository;
import com.example.gara_management.service.ServicesService;
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
public class ServicesServiceImpl implements ServicesService {

    private final ServiceRepository serviceRepo;

    private final ModelMapper modelMapper;

    @Override
    public JpaRepository<Services, Integer> getRepository() {
        return serviceRepo;
    }

    @Override
    public ServiceResponse createService(ServiceRequest request) {
        Services services = Services.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        serviceRepo.save(services);
        return modelMapper.map(services, ServiceResponse.class);
    }

    @Override
    public ServiceResponse updateService(Integer id, ServiceRequest request) {
        Services services = serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found service with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        modelMapper.map(request, services);
        serviceRepo.save(services);
        return modelMapper.map(services, ServiceResponse.class);
    }

    @Override
    public ServiceResponse getService(Integer id) {
        Services services = serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found service with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        return modelMapper.map(services, ServiceResponse.class);
    }

    @Override
    public void delete(Integer id) {
        Services services = serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found service with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        serviceRepo.delete(services);
    }

    @Override
    public PageResponse<ServiceResponse> listOrSearch(String name, Pageable pageable) {
        BaseEntityFilter<ServicesFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        if (StringUtils.isNotBlank(name)) {
            filter.getFilters().add(ServicesFilter.builder()
                    .nameLk(MyStringUtils.buildSqlLikePattern(name))
                    .build());
        }
        return PageResponse
                .toResponse(filter(filter), service -> modelMapper.map(service, ServiceResponse.class));
    }

}