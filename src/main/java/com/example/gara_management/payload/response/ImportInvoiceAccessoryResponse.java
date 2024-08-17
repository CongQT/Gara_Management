package com.example.gara_management.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportInvoiceAccessoryResponse {

  @JsonProperty("accessory_id")
  private Integer accessoryId;

  @JsonProperty("import_price")
  private Double import_price;

  @JsonProperty("quantity")
  private Integer quantity;

}