package com.example.gara_management.service;

import com.example.gara_management.entity.Accessory;
import com.example.gara_management.entity.ImportInvoice;
import com.example.gara_management.enumtype.ImportInvoiceStatus;
import com.example.gara_management.payload.request.AccessoryRequest;
import com.example.gara_management.payload.request.ImportInvoiceRequest;
import com.example.gara_management.payload.response.AccessoryResponse;
import com.example.gara_management.payload.response.ImportInvoiceResponse;
import com.example.gara_management.payload.response.base.PageResponse;
import org.springframework.data.domain.Pageable;


public interface ImportInvoiceService extends BaseEntityService<ImportInvoice, Integer>{

    ImportInvoiceResponse createImportInvoice(Integer supplierId, ImportInvoiceRequest request);

    void updateStatus(Integer id, ImportInvoiceStatus status);

    ImportInvoiceResponse getImportInvoice(Integer id);

    PageResponse<ImportInvoiceResponse> listOrSearch(Pageable pageable);
}
