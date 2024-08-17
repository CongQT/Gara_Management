package com.example.gara_management.payload.request;

import com.example.gara_management.enumtype.ImportInvoiceStatus;
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
public class ImportInvoiceRequest {

  @JsonProperty("note")
  private String note;

  @JsonProperty("import_invoice_accessory")
  private List<ImportInvoiceAccessoryRequest> importInvoiceAccessories;

}