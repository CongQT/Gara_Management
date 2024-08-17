package com.example.gara_management.service.impl;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.Accessory;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.filter.BaseEntityFilter;
import com.example.gara_management.payload.filter.AccessoryFilter;
import com.example.gara_management.payload.request.AccessoryRequest;
import com.example.gara_management.payload.response.AccessoryResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.repository.AccessoryRepository;
import com.example.gara_management.service.AccessoryService;
import com.example.gara_management.util.MyStringUtils;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepo;

    private final ModelMapper modelMapper;

    @Override
    public JpaRepository<Accessory, Integer> getRepository() {
        return accessoryRepo;
    }

    @Override
    public AccessoryResponse createAccessory(AccessoryRequest request) {
        Accessory accessory = Accessory.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .quantity(0)
                .build();
        accessoryRepo.save(accessory);
        return modelMapper.map(accessory, AccessoryResponse.class);
    }

    @Override
    @CacheEvict(value = "accessories", key = "#id")
    public AccessoryResponse updateAccessory(Integer id, AccessoryRequest request) {
        Accessory accessory = accessoryRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found accessory with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        modelMapper.map(request, accessory);
        accessoryRepo.save(accessory);
        return modelMapper.map(accessory, AccessoryResponse.class);
    }

    @Override
    @Cacheable(value = "accessories", key = "#id")
    public AccessoryResponse getAccessory(Integer id) {
        Accessory accessory = accessoryRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found accessory with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        return modelMapper.map(accessory, AccessoryResponse.class);
    }

    @Override
    @CacheEvict(value = "accessories", key = "#id")
    public void delete(Integer id) {
        Accessory accessory = accessoryRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found service with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        accessoryRepo.delete(accessory);
    }

    @Override
    public PageResponse<AccessoryResponse> listOrSearch(String name, Pageable pageable) {
        BaseEntityFilter<AccessoryFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        if (StringUtils.isNotBlank(name)) {
            filter.getFilters().add(AccessoryFilter.builder()
                    .nameLk(MyStringUtils.buildSqlLikePattern(name))
                    .build());
        }
        return PageResponse
                .toResponse(filter(filter), accessory -> modelMapper.map(accessory, AccessoryResponse.class));
    }
    
}