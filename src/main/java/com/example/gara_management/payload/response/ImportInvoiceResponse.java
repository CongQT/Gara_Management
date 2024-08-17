package com.example.gara_management.payload.response;

import com.example.gara_management.enumtype.ImportInvoiceStatus;
import com.example.gara_management.payload.request.ImportInvoiceAccessoryRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
public class ImportInvoiceResponse {

  @JsonProperty("note")
  private String note;

  @JsonProperty("total_amount")
  private Double totalAmount;

  @JsonProperty("note")
  private ImportInvoiceStatus status;

  @Column(name = "date")
  private LocalDateTime date;

  @JsonProperty("import_invoice_accessory")
  private List<ImportInvoiceAccessoryResponse> importInvoiceAccessory;

}