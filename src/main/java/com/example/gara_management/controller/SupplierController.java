package com.example.gara_management.controller;

import com.example.gara_management.payload.request.SupplierRequest;
import com.example.gara_management.payload.response.SupplierResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/create_supplier")
    public BaseResponse<SupplierResponse> createSupplier(@RequestBody @Valid SupplierRequest request) {
        return BaseResponse.of(supplierService.createSupplier(request));
    }

    @PostMapping("/update_supplier")
    public BaseResponse<SupplierResponse> updateSupplier(
            @RequestParam(name = "id") Integer id,
            @RequestBody @Valid SupplierRequest request) {
        return BaseResponse.of(supplierService.updateSupplier(id, request));
    }

    @GetMapping("/get_supplier")
    public BaseResponse<SupplierResponse> getSupplier(@RequestParam(name = "id") Integer id) {
        return BaseResponse.of(supplierService.getSupplier(id));
    }

    @GetMapping("/list_or_search")
    public BaseResponse<PageResponse<SupplierResponse>> listOrSearch(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return BaseResponse.of(
                supplierService.listOrSearch(name, pageable));
    }
}
