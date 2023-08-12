package com.sidof.model;

import com.sidof.model.enumes.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 19/05/2023
 * @Version v1.0
 */
@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Sale {
    @Id @SequenceGenerator(name = "sequence_id_sale",allocationSize = 1,sequenceName = "sequence_id_sale") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_sale")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product ;
    private int quantity;
    private double amount;
    private LocalDate orderAt;
    private LocalDate arrivesAt;
    private boolean isArrive;
    private Status status;
}
