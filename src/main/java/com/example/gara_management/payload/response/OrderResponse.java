package com.example.gara_management.payload.response;

import com.example.gara_management.payload.request.OrderAccessoryRequest;
import com.example.gara_management.payload.request.OrderServiceRequest;
import com.example.gara_management.payload.request.UserInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {

  @JsonProperty("client")
  private UserInfoResponse client;

  @JsonProperty("staff")
  private UserInfoResponse staff;

  @JsonProperty("date")
  private LocalDateTime date;

  @JsonProperty("order_service")
  private List<OrderServiceResponse> orderServices;

  @JsonProperty("order_accessory")
  private List<OrderAccessoryResponse> orderAccessories;

}