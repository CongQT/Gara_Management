package com.example.gara_management.controller;

import com.example.gara_management.payload.request.OrderRequest;
import com.example.gara_management.payload.response.OrderResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public BaseResponse<OrderResponse> createOrder(
            @RequestParam(name = "clientId") Integer clientId,
            @RequestParam(name = "staffId") Integer staffId,
            @RequestBody @Valid OrderRequest request) {
        return BaseResponse.of(orderService.createOrder(clientId, staffId, request));
    }

    @DeleteMapping("/detele")
    public BaseResponse<Object> delete(@RequestParam(name = "id") Integer id) {
        orderService.delete(id);
        return BaseResponse.of(Collections.emptyMap());
    }

    @GetMapping("/get")
    public BaseResponse<OrderResponse> getOrder(@RequestParam(name = "id") Integer id) {
        return BaseResponse.of(orderService.getOrder(id));
    }

    @GetMapping("/list_or_search")
    public BaseResponse<PageResponse<OrderResponse>> listOrSearch(
            Pageable pageable
    ) {
        return BaseResponse.of(
                orderService.listOrSearch(pageable));
    }
}
