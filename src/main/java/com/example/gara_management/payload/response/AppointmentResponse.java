package com.example.gara_management.payload.response;

import com.example.gara_management.enumtype.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentResponse {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("status")
  private AppointmentStatus status;

  @JsonProperty("note")
  private Double note;

  @JsonProperty("date")
  private LocalDateTime date;

  @JsonProperty("client")
  private UserInfoResponse user;

}