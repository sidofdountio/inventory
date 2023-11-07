package com.sidof.model;

import com.sidof.model.enumes.InvoiceStatus;
import com.sidof.model.enumes.InvoiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 30/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class InvoiceSale {
    @Id
    @SequenceGenerator(name = "sequence_id_invoice_sale", allocationSize = 1, sequenceName = "sequence_id_invoice_sale")
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence_id_invoice_sale")
    private Long id;
    @Enumerated(STRING)
    private InvoiceType invoiceType;
    @Enumerated(STRING)
    private InvoiceStatus invoiceStatus;
    private double subTotal;
    private double tax;
    private double total;
    private String invoiceNumber;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "invoice_sale"))
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "invoice_custorme"))
    private Customer customer;

}
