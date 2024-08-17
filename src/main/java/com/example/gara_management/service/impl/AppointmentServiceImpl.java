package com.example.gara_management.service.impl;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.Appointment;
import com.example.gara_management.entity.User;
import com.example.gara_management.enumtype.AppointmentStatus;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.filter.AppointmentFilter;
import com.example.gara_management.payload.filter.BaseEntityFilter;
import com.example.gara_management.payload.request.AppointmentRequest;
import com.example.gara_management.payload.response.AppointmentResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import com.example.gara_management.repository.AppointmentRepository;
import com.example.gara_management.repository.UserRepository;
import com.example.gara_management.security.core.userdetails.impl.UserDetailsImpl;
import com.example.gara_management.service.AppointmentService;
import com.example.gara_management.util.MyStringUtils;
import com.example.gara_management.util.SecurityUtils;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final UserRepository userRepo;

    private final ModelMapper modelMapper;

    @Override
    public JpaRepository<Appointment, Integer> getRepository() {
        return appointmentRepo;
    }

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest request) {
        User user = SecurityUtils.getUserDetails(UserDetailsImpl.class).getUser();
        Appointment appointment = Appointment.builder()
                .note(request.getNote())
                .date(request.getDate())
                .status(AppointmentStatus.NOT_VERIFIED)
                .user(user)
                .build();
        appointmentRepo.save(appointment);
        return modelMapper.map(appointment, AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse getAppointment(Integer id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found appointment with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        return modelMapper.map(appointment, AppointmentResponse.class);
    }

    @Override
    public void updateStatus(Integer id, AppointmentStatus status) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found service with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("id", id));
        appointment.setStatus(status);
        appointmentRepo.save(appointment);
    }

    @Override
    public PageResponse<AppointmentResponse> getAllByClient(Pageable pageable) {
        BaseEntityFilter<AppointmentFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        return PageResponse
                .toResponse(filter(filter), appointment -> modelMapper.map(appointment, AppointmentResponse.class));
    }

    @Override
    public PageResponse<AppointmentResponse> listOrSearch(Integer userId, AppointmentStatus status, Pageable pageable) {
        BaseEntityFilter<AppointmentFilter> filter = BaseEntityFilter.of(Lists.newArrayList(), pageable);
        if (status != null || userId != null ) {
            AppointmentFilter appointmentFilter = new AppointmentFilter();
            if (status != null) {
                appointmentFilter.setStatus(status);
            }
            if (userId != null) {
                User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("Not found user with id: " + userId)
                        .errorCode(ErrorCode.USER_NOT_FOUND)
                        .extraData("id", userId));
                appointmentFilter.setUser(user);
            }
            filter.getFilters().add(appointmentFilter);
        }
        return PageResponse
                .toResponse(filter(filter), appointment -> modelMapper.map(appointment, AppointmentResponse.class));
    }

}