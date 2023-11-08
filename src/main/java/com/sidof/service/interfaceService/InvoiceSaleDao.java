package com.sidof.service.interfaceService;

import com.sidof.model.InvoiceSale;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface InvoiceSaleDao {
    List<InvoiceSale> getInvoiceSales();
    List<InvoiceSale> getInvoicesSaleBySaleId(Long saleId);
    InvoiceSale getInvoicesSaleByCustormeId(Long custormeId);
    List<InvoiceSale> getInvoicesSaleByCustormeIds(Long custormeId);
    List<InvoiceSale> addInvoiceSale(List<InvoiceSale> invoiceSales);

}
