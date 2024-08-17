package com.example.gara_management.controller;

import com.example.gara_management.payload.request.AccessoryRequest;
import com.example.gara_management.payload.request.ServiceRequest;
import com.example.gara_management.payload.response.AccessoryResponse;
import com.example.gara_management.payload.response.ServiceResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.service.AccessoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/accessory")
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;

    @PostMapping("/create_accessory")
    public BaseResponse<AccessoryResponse> createAccessory(@RequestBody @Valid AccessoryRequest request) {
        return BaseResponse.of(accessoryService.createAccessory(request));
    }

    @PostMapping("/update_accessory")
    public BaseResponse<AccessoryResponse> updateAccessory(
            @RequestParam(name = "id") Integer id,
            @RequestBody @Valid AccessoryRequest request) {
        return BaseResponse.of(accessoryService.updateAccessory(id, request));
    }

    @GetMapping("/get_accessory")
    public BaseResponse<AccessoryResponse> getAccessory(@RequestParam(name = "id") Integer id) {
        return BaseResponse.of(accessoryService.getAccessory(id));
    }

    @GetMapping("/list_or_search")
    public BaseResponse<PageResponse<AccessoryResponse>> listOrSearch(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return BaseResponse.of(
                accessoryService.listOrSearch(name, pageable));
    }
}
