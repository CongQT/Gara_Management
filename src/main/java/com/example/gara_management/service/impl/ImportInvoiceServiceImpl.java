package com.example.gara_management.service.impl;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.ImportInvoice;
import com.example.gara_management.entity.ImportInvoiceAccessory;
import com.example.gara_management.entity.Supplier;
import com.example.gara_management.enumtype.ImportInvoiceStatus;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.filter.AccessoryFilter;
import com.example.gara_management.payload.filter.BaseEntityFilter;
import com.example.gara_management.payload.filter.ImportInvoiceFilter;
import com.example.gara_management.payload.request.ImportInvoiceRequest;
import com.example.gara_management.payload.response.AccessoryResponse;
import com.example.gara_management.payload.response.ImportInvoiceResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.repository.ImportInvoiceAccessoryRepository;
import com.example.gara_management.repository.ImportInvoiceRepository;
import com.example.gara_management.repository.SupplierRepository;
import com.example.gara_management.service.ImportInvoiceService;
import com.example.gara_management.util.MyStringUtils;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ImportInvoiceServiceImpl implements ImportInvoiceService {
    private final ImportInvoiceRepository importInvoiceRepo;
    private final SupplierRepository supplierRepo;
    private final ImportInvoiceAccessoryRepository importInvoiceAccessoryRepo;

    private final ModelMapper modelMapper;

    @Override
    public JpaRepository<ImportInvoice, Integer> getRepository() {
        return importInvoiceRepo;
    }

    @Override
    public ImportInvoiceResponse createImportInvoice(Integer supplierId, ImportInvoiceRequest request) {
        Supplier supplier = supplierRepo.findById(supplierId).orElseThrow(() -> new NotFoundException("Not found supplier with id: " + supplierId)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", supplierId));
        ImportInvoice importInvoice = ImportInvoice.builder()
                .date(LocalDateTime.now())
                .note(request.getNote())
                .supplier(supplier)
                .status(ImportInvoiceStatus.NOT_PAID)
                .build();
        importInvoiceRepo.save(importInvoice);
        Double[] total = {0.0};
        List<ImportInvoiceAccessory> importInvoiceAccessories = new ArrayList<>();
        request.getImportInvoiceAccessories().forEach(importInvoiceAccessoryRequest -> {
            ImportInvoiceAccessory importInvoiceAccessory = ImportInvoiceAccessory.builder()
                    .importInvoiceId(importInvoice.getId())
                    .accessoryId(importInvoiceAccessoryRequest.getAccessoryId())
                    .quantity(importInvoiceAccessoryRequest.getQuantity())
                    .import_price(importInvoiceAccessoryRequest.getImportPrice())
                    .build();
            importInvoiceAccessoryRepo.save(importInvoiceAccessory);
            importInvoiceAccessories.add(importInvoiceAccessory);
            total[0] += importInvoiceAccessoryRequest.getImportPrice() * importInvoiceAccessoryRequest.getQuantity();
        });
        importInvoice.setImportInvoiceAccessories(importInvoiceAccessories);
        importInvoice.setTotalAmount(total[0]);
        importInvoiceRepo.save(importInvoice);
        return modelMapper.map(importInvoice, ImportInvoiceResponse.class);
    }

    @Override
    public void updateStatus(Integer id, ImportInvoiceStatus status) {
        ImportInvoice importInvoice = importInvoiceRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found import invoice with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        importInvoice.setStatus(status);
        importInvoiceRepo.save(importInvoice);
    }

    @Override
    public ImportInvoiceResponse getImportInvoice(Integer id) {
        ImportInvoice importInvoice = importInvoiceRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found import invoice with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        return modelMapper.map(importInvoice, ImportInvoiceResponse.class);
    }

    @Override
    public PageResponse<ImportInvoiceResponse> listOrSearch(Pageable pageable) {
        BaseEntityFilter<ImportInvoiceFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        return PageResponse
                .toResponse(filter(filter), importInvoice -> modelMapper.map(importInvoice, ImportInvoiceResponse.class));
    }
}
