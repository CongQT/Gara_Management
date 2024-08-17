package com.example.gara_management.payload.filter;

import com.example.gara_management.entity.User;
import com.example.gara_management.enumtype.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentFilter {
    private User user;
    private AppointmentStatus status;
}
