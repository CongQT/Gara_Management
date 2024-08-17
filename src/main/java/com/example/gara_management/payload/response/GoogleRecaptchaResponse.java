package com.example.gara_management.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GoogleRecaptchaResponse {

  private boolean success;
  private String hostname;

  @JsonProperty("challenge-ts")
  private String challengeTs;

  @JsonProperty("error-codes")
  private List<String> errorCodes;

  private Double score;
  private String action;
}
