package com.sidof.service;

import com.sidof.model.InvoiceSale;
import com.sidof.repo.InvoiceSaleRepo;
import com.sidof.service.interfaceService.InvoiceSaleDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceSaleService implements InvoiceSaleDao {
    private final InvoiceSaleRepo invoiceSaleRepo;

    @Override
    public List<InvoiceSale> getInvoiceSales() {
        log.info("Fetching  sale invoice.");

        return invoiceSaleRepo.findAll();
    }

    @Override
    public
    List<InvoiceSale> addInvoiceSale(List<InvoiceSale> invoiceSale) {
        log.info("saved new invoice sale.");
        return invoiceSaleRepo.saveAll(invoiceSale);
    }

    @Override
    public List<InvoiceSale> getInvoicesSaleBySaleId(Long saleId) {
        List<InvoiceSale> bySaleId = invoiceSaleRepo.findBySaleId(saleId);

        log.info("Fetching  invoice sale saleId : {}.", saleId);
        return bySaleId;
    }

    /**
     * @param custormeId
     * @return
     */
    @Override
    public List<InvoiceSale> getInvoicesSaleByCustormeIds(Long custormeId) {
        log.info("Fetching invoice sale by {}",custormeId);
        return invoiceSaleRepo.findAllByCustomerId(custormeId);
    }

    /**
     * @param custormeId 
     * @return
     */
    @Override
    public InvoiceSale getInvoicesSaleByCustormeId(Long custormeId) {
        log.info("Fetching invoice sale by {}",custormeId);
        return null;
    }
}
