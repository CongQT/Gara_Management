package com.example.gara_management.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportInvoiceAccessoryRequest {

  @JsonProperty("accessory_id")
  private Integer accessoryId;

  @JsonProperty("import_price")
  private Double importPrice;

  @JsonProperty("quantity")
  private Integer quantity;

}