package com.example.gara_management.controller;

import com.example.gara_management.payload.request.BillRequest;
import com.example.gara_management.payload.response.BillResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/create")
    public BaseResponse<BillResponse> createBill(
            @RequestParam(name = "clientId") Integer clientId,
            @RequestParam(name = "orderId") Integer orderId,
            @RequestBody @Valid BillRequest request) {
        return BaseResponse.of(billService.createBill(clientId, orderId, request));
    }

    @DeleteMapping("/detele")
    public BaseResponse<Object> delete(@RequestParam(name = "id") Integer id) {
        billService.delete(id);
        return BaseResponse.of(Collections.emptyMap());
    }

    @GetMapping("/get")
    public BaseResponse<BillResponse> getBill(@RequestParam(name = "id") Integer id) {
        return BaseResponse.of(billService.getBill(id));
    }

    @GetMapping("/list_or_search")
    public BaseResponse<PageResponse<BillResponse>> listOrSearch(
            Pageable pageable
    ) {
        return BaseResponse.of(
                billService.listOrSearch(pageable));
    }

    @GetMapping("/list_by_client")
    public BaseResponse<PageResponse<BillResponse>> listByClient(
            Pageable pageable
    ) {
        return BaseResponse.of(
                billService.listByClient(pageable));
    }
}
