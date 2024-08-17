package com.example.gara_management.payload.request;

import com.example.gara_management.payload.response.UserInfoResponse;
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
public class BillRequest {

  @JsonProperty("total_amount")
  private Double totalAmount;

  @JsonProperty("note")
  private String note;

  @JsonProperty("date")
  private LocalDateTime date;

}