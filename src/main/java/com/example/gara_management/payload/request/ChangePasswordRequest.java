package com.example.gara_management.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordRequest {

  @NotBlank
  @JsonProperty("old_password")
  private String oldPassword;

  @Size(min = 6, message = "MIN_LENGTH_INVALID")
  @NotBlank(message = "MISSING_PASSWORD")
  @JsonProperty("password")
  private String password;

}