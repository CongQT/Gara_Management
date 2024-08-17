package com.example.gara_management.service.impl;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.*;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.filter.BaseEntityFilter;
import com.example.gara_management.payload.filter.ImportInvoiceFilter;
import com.example.gara_management.payload.filter.OrderFilter;
import com.example.gara_management.payload.request.OrderRequest;
import com.example.gara_management.payload.response.ImportInvoiceResponse;
import com.example.gara_management.payload.response.OrderResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.repository.*;
import com.example.gara_management.service.OrderService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final OrderAccessoryRepository orderAccessoryRepo;
    private final OrderServiceRepository orderServiceRepo;
    private final UserRepository userRepo;

    private final ModelMapper modelMapper;


    @Override
    public JpaRepository<Order, Integer> getRepository() {
        return orderRepo;
    }

    @Override
    public OrderResponse createOrder(Integer clientId, Integer staffId, OrderRequest request) {
        User client = userRepo.findById(clientId).orElseThrow(() -> new NotFoundException("Not found user with id: " + clientId)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", clientId));
        User staff = userRepo.findById(staffId).orElseThrow(() -> new NotFoundException("Not found user with id: " + staffId)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", staffId));
        Order order = Order.builder()
                .client(client)
                .staff(staff)
                .date(LocalDateTime.now())
                .build();
        orderRepo.save(order);
        List<OrderServices> orderServices = new ArrayList<>();
        request.getOrderServices().forEach(orderServiceRequest -> {
            OrderServices orderService = OrderServices.builder()
                    .serviceId(orderServiceRequest.getServiceId())
                    .orderId(order.getId())
                    .build();
            orderServices.add(orderService);
            orderServiceRepo.save(orderService);
        });
        List<OrderAccessory> orderAccessories = new ArrayList<>();
        request.getOrderAccessories().forEach(orderAccessoryRequest -> {
            OrderAccessory orderAccessory = OrderAccessory.builder()
                    .accessoryId(orderAccessoryRequest.getAccesoryId())
                    .orderId(order.getId())
                    .quantity(orderAccessoryRequest.getQuantity())
                    .build();
            orderAccessories.add(orderAccessory);
            orderAccessoryRepo.save(orderAccessory);
        });
        order.setOrderServices(orderServices);
        order.setOrderAccessories(orderAccessories);
        orderRepo.save(order);

        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse getOrder(Integer id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found order with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public void delete(Integer id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found order with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        orderRepo.delete(order);
    }

    @Override
    public PageResponse<OrderResponse> listOrSearch(Pageable pageable) {
        BaseEntityFilter<OrderFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        return PageResponse
                .toResponse(filter(filter), order -> modelMapper.map(order, OrderResponse.class));
    }

}
