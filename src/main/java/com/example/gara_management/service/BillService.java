package com.example.gara_management.service;

import com.example.gara_management.entity.Bill;
import com.example.gara_management.entity.Order;
import com.example.gara_management.payload.request.BillRequest;
import com.example.gara_management.payload.request.OrderRequest;
import com.example.gara_management.payload.response.BillResponse;
import com.example.gara_management.payload.response.OrderResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import org.springframework.data.domain.Pageable;


public interface BillService extends BaseEntityService<Bill, Integer>{

    BillResponse createBill(Integer clientId, Integer orderId, BillRequest request);

    BillResponse getBill(Integer id);

    void delete(Integer id);

    PageResponse<BillResponse> listOrSearch(Pageable pageable);

    PageResponse<BillResponse> listByClient(Pageable pageable);
}
