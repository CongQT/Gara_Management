package com.example.gara_management.payload.response;

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
public class BillResponse {

  @JsonProperty("client")
  private UserInfoResponse client;

  @JsonProperty("orders_id")
  private Integer orderId;

  @JsonProperty("total_amount")
  private Double totalAmount;

  @JsonProperty("note")
  private String note;

  @JsonProperty("date")
  private LocalDateTime date;

}