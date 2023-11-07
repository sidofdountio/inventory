package com.sidof.api;

import com.sidof.model.Inventory;
import com.sidof.model.InvoiceSale;
import com.sidof.model.Response;
import com.sidof.model.Sale;
import com.sidof.operation.InventoryOperation;
import com.sidof.service.InventoryService;
import com.sidof.service.InvoiceSaleService;
import com.sidof.service.ProductService;
import com.sidof.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.sidof.model.enumes.InvoiceStatus.PENDING;
import static com.sidof.model.enumes.InvoiceType.SALE;
import static com.sidof.model.enumes.Status.INPROGRESS;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RequiredArgsConstructor
@Transactional
public class SaleApi {
    private final SaleService saleService;
    private final InventoryService inventoryService;
    private final ProductService productService;
    private final InventoryOperation inventoryOperation;
    private final InvoiceSaleService invoiceSaleService;

    public static String generateInvoiceNumber() {
        UUID uuid = UUID.randomUUID();
        String invoiceNumber = uuid.toString().replace("-", "").substring(0, 10);
        return invoiceNumber;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getSales() {
        final List<Sale> sales = saleService.SALES();
        return new ResponseEntity<>(sales, OK);
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<Response> getSale(@PathVariable("id") Long id) throws InterruptedException {
        var response = Response.builder()
                .timeStamp(now())
                .message("Fetch current sale")
                .status(OK)
                .statusCode(OK.value())
                .data(of("sale", saleService.getSale(id)))
                .build();
//        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saleById/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id") Long id) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(saleService.getSale(id));
    }

    @PostMapping("/addSale")
    public ResponseEntity<List<Sale>> addSale(@RequestBody List<Sale> saleToSave) throws InterruptedException {
        double total = 0;

        List<InvoiceSale> invoiceSales = new ArrayList<>();
        final List<Inventory> inventoryList = inventoryService.INVENTORIES();
        final List<Inventory> cmupForSale = inventoryOperation.cmupForSale("Sale", saleToSave, inventoryList);

//        if (cmupForSale == null) {
//            throw new NullPointerException("error");
//        }

        for (Inventory inventory: cmupForSale) {
            for (Sale sale: saleToSave) {
                sale.setOrderAt(inventory.getDate());
                sale.setPrice(inventory.getOrldPrice());
                sale.setAmount(inventory.getOldAmount());
                sale.setStatus(INPROGRESS);
            }
        }

//        saved inventory.
//        inventoryService.addInventoryForSale(cmupForSale);
//        saved sale
//        final List<Sale> saleSaved = saleService.addSale(saleToSave);
        final List<Sale> saleSaved = new ArrayList<>();

//  Set invoice sale propeties.
        for (Sale sale: saleSaved) {
            total += sale.getAmount();
            var invoiceSale = InvoiceSale.builder()
                    .sale(sale)
                    .customer(sale.getCustomer())
                    .tax(0)
                    .subTotal(total)
                    .total(total)
                    .invoiceType(SALE)
                    .invoiceNumber(generateInvoiceNumber())
                    .invoiceStatus(PENDING)
                    .date(LocalDate.now())
                    .build();
            invoiceSales.add(invoiceSale);
        }
//        saved invoice
//        invoiceSaleService.addInvoiceSale(invoiceSales);
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity<>(saleSaved, CREATED);
    }
}