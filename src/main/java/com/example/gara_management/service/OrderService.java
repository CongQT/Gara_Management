package com.example.gara_management.service;

import com.example.gara_management.entity.ImportInvoice;
import com.example.gara_management.entity.Order;
import com.example.gara_management.enumtype.ImportInvoiceStatus;
import com.example.gara_management.payload.request.ImportInvoiceRequest;
import com.example.gara_management.payload.request.OrderRequest;
import com.example.gara_management.payload.response.ImportInvoiceResponse;
import com.example.gara_management.payload.response.OrderResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import org.springframework.data.domain.Pageable;


public interface OrderService extends BaseEntityService<Order, Integer>{

    OrderResponse createOrder(Integer clientId, Integer staffId, OrderRequest request);

    OrderResponse getOrder(Integer id);

    void delete(Integer id);

    PageResponse<OrderResponse> listOrSearch(Pageable pageable);
}
