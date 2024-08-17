package com.example.gara_management.service.impl;

import com.example.gara_management.config.properties.MailProperties;
import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.*;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.mail.message.MailMessage;
import com.example.gara_management.payload.filter.AccessoryFilter;
import com.example.gara_management.payload.filter.BaseEntityFilter;
import com.example.gara_management.payload.filter.BillFilter;
import com.example.gara_management.payload.filter.OrderFilter;
import com.example.gara_management.payload.request.BillRequest;
import com.example.gara_management.payload.request.OrderRequest;
import com.example.gara_management.payload.response.BillResponse;
import com.example.gara_management.payload.response.OrderResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.repository.*;
import com.example.gara_management.security.core.userdetails.impl.UserDetailsImpl;
import com.example.gara_management.service.BillService;
import com.example.gara_management.service.MailService;
import com.example.gara_management.service.OrderService;
import com.example.gara_management.util.MyStringUtils;
import com.example.gara_management.util.SecurityUtils;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final MailProperties mailProperties;

    private final OrderRepository orderRepo;
    private final OrderAccessoryRepository orderAccessoryRepo;
    private final OrderServiceRepository orderServiceRepo;
    private final UserRepository userRepo;
    private final BillRepository billRepo;
    private final ServiceRepository serviceRepo;
    private final AccessoryRepository accessoryRepo;

    private final MailService mailService;

    private final ModelMapper modelMapper;


    @Override
    public JpaRepository<Bill, Integer> getRepository() {
        return billRepo;
    }

    @Override
    public BillResponse createBill(Integer clientId, Integer orderId, BillRequest request) {
        User client = userRepo.findById(clientId).orElseThrow(() -> new NotFoundException("Not found user with id: " + clientId)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", clientId));
        Bill bill = Bill.builder()
                .client(client)
                .orderId(orderId)
                .note(request.getNote())
                .date(LocalDateTime.now())
                .build();
        billRepo.save(bill);
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new NotFoundException("Not found order with id: " + orderId)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", orderId));
        Double totalService = order.getOrderServices().stream().mapToDouble(orderServices -> {
            Services orderServices1 = serviceRepo.findById(orderServices.getServiceId()).orElseThrow(() -> new NotFoundException("Not found service with id: " + orderServices.getServiceId())
                    .errorCode(ErrorCode.USER_NOT_FOUND)
                    .extraData("id", orderServices.getServiceId()));
            return orderServices1.getPrice();
        }).sum();
        Double totalAccessory = order.getOrderAccessories().stream().mapToDouble(orderAccessory -> {
            Accessory accessory = accessoryRepo.findById(orderAccessory.getAccessoryId()).orElseThrow(() -> new NotFoundException("Not found accessory with id: " + orderAccessory.getAccessoryId())
                    .errorCode(ErrorCode.USER_NOT_FOUND)
                    .extraData("id", orderAccessory.getAccessoryId()));
            return accessory.getPrice() * orderAccessory.getQuantity();
        }).sum();
        bill.setTotalAmount(totalService + totalAccessory);
        billRepo.save(bill);

        mailService.send(MailMessage.builder()
                .senderName(mailProperties.getSenderName())
                .senderAddress(mailProperties.getSenderAddress())
                .subject("Dịch vụ garaman")
                .content(
                        "Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi. Nếu có điều gì không hài lòng, vui lòng liên hệ số điện thoại 0123456789. Hẹn gặp lại quý khách.")
                .receivers(new HashSet<>(Arrays.asList(client.getEmail())))
                .build());
        return modelMapper.map(bill, BillResponse.class);
    }

    @Override
    public BillResponse getBill(Integer id) {
        Bill bill = billRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found bill with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        return modelMapper.map(bill, BillResponse.class);
    }

    @Override
    public void delete(Integer id) {
        Bill bill = billRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found bill with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        billRepo.delete(bill);
    }

    @Override
    public PageResponse<BillResponse> listOrSearch(Pageable pageable) {
        BaseEntityFilter<BillFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        return PageResponse
                .toResponse(filter(filter), bill -> modelMapper.map(bill, BillResponse.class));
    }

    @Override
    public PageResponse<BillResponse> listByClient(Pageable pageable) {
        User user = SecurityUtils.getUserDetails(UserDetailsImpl.class).getUser();
        BaseEntityFilter<BillFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        filter.getFilters().add(BillFilter.builder()
                .client(user)
                .build());
        return PageResponse
                .toResponse(filter(filter), bill -> modelMapper.map(bill, BillResponse.class));
    }


}
