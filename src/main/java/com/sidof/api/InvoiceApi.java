package com.sidof.api;

import com.sidof.model.InvoiceSale;
import com.sidof.model.Response;
import com.sidof.service.InvoiceSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @Author sidof
 * @Since 31/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600, methods ={
        DELETE, GET, OPTIONS, POST, PUT
})
public class InvoiceApi {
    private final InvoiceSaleService invoiceSaleService;

    @GetMapping("/invoice")
    public ResponseEntity<Response> getInvoiceSale() throws InterruptedException {
        final List<InvoiceSale> invoiceSales = invoiceSaleService.getInvoiceSales();
        var response = Response.builder()
                .timeStamp(now())
                .message("Fetch invoiceSales")
                .status(OK)
                .statusCode(OK.value())
                .data(of("invoice", invoiceSales))
                .build();
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceSale>> getInvoiceSales() throws InterruptedException {
        final List<InvoiceSale> invoiceSales = invoiceSaleService.getInvoiceSales();
        return new  ResponseEntity<List<InvoiceSale>>(invoiceSales,OK);
    }


    @GetMapping("/invoices/{saleId}")
    public ResponseEntity<List<InvoiceSale>> getInvoiceSaleById(@PathVariable("saleId")Long saleId) throws InterruptedException {
        final List<InvoiceSale> invoiceSaleBySaleId = invoiceSaleService.getInvoicesSaleBySaleId(saleId);
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity<List<InvoiceSale>>(invoiceSaleBySaleId,OK);
    }
    @GetMapping("/invoice/{customerId}")
    public ResponseEntity<List<InvoiceSale>> getInvoiceCustomerByIds(@PathVariable("customerId")Long customerId) throws InterruptedException {
        final List<InvoiceSale> invoicesSaleByCustormeId = invoiceSaleService.getInvoicesSaleByCustormeIds(customerId);
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity<List<InvoiceSale>>(invoicesSaleByCustormeId,OK);
    }
//    @GetMapping("/invoice/customer/{customerId}")
//    public ResponseEntity<InvoiceSale> getInvoiceCustomerById(@PathVariable("customerId")Long customerId) throws InterruptedException {
//        final InvoiceSale invoicesSaleByCustormeId = invoiceSaleService.getInvoicesSaleByCustormeId(customerId);
//        TimeUnit.SECONDS.sleep(1);
//        return new ResponseEntity<InvoiceSale>(invoicesSaleByCustormeId,OK);
//    }
}
