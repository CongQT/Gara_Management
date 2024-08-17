package com.example.gara_management.controller;

import com.example.gara_management.enumtype.ImportInvoiceStatus;
import com.example.gara_management.payload.request.ImportInvoiceRequest;
import com.example.gara_management.payload.response.ImportInvoiceResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.service.ImportInvoiceService;
import com.example.gara_management.service.ImportInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/import_invoice")
@RequiredArgsConstructor
public class ImportInvoiceController {

    private final ImportInvoiceService importInvoiceService;

    @PostMapping("/create")
    public BaseResponse<ImportInvoiceResponse> createImportInvoice(
            @RequestParam(name = "supplierId") Integer supplierId,
            @RequestBody @Valid ImportInvoiceRequest request) {
        return BaseResponse.of(importInvoiceService.createImportInvoice(supplierId, request));
    }

    @PostMapping("/update_status")
    public BaseResponse<Object> updateStatus(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "status") ImportInvoiceStatus status) {
        importInvoiceService.updateStatus(id, status);
        return BaseResponse.of(Collections.emptyMap());
    }

    @GetMapping("/get")
    public BaseResponse<ImportInvoiceResponse> getImportInvoice(@RequestParam(name = "id") Integer id) {
        return BaseResponse.of(importInvoiceService.getImportInvoice(id));
    }

    @GetMapping("/list_or_search")
    public BaseResponse<PageResponse<ImportInvoiceResponse>> listOrSearch(
            Pageable pageable
    ) {
        return BaseResponse.of(
                importInvoiceService.listOrSearch(pageable));
    }
}
