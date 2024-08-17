package com.example.gara_management.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class RegistrationRequest {

  @NotBlank(message = "MISSING_USERNAME")
  @JsonProperty("username")
  private String username;

  @Size(min = 6, message = "MIN_LENGTH_INVALID")
  @NotBlank(message = "MISSING_PASSWORD")
  @JsonProperty("password")
  private String password;

  @Pattern(regexp = ".+@.+\\..+", message = "INVALID_EMAIL")
  @JsonProperty("email")
  private String email;

  @JsonProperty("role_id")
  private Integer roleId;

  @NotBlank(message = "MISSING_FULLNAME")
  @JsonProperty("full_name")
  private String fullName;

  @NotBlank(message = "MISSING_PHONE")
  @JsonProperty("phone")
  private String phone;

  @NotBlank(message = "MISSING_ADDRESS")
  @JsonProperty("address")
  private String address;

}