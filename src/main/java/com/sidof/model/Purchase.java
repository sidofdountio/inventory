package com.sidof.model;

import com.sidof.model.enumes.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 17/05/2023
 * @Version v1.0
 */
@Data @AllArgsConstructor @NoArgsConstructor @Entity @Builder
public class Purchase {
    @Id @SequenceGenerator(name = "sequence_id_purchase",allocationSize = 1,sequenceName = "sequence_id_purchase") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_purchase")
    private Long id;
    @ManyToOne @JoinColumn(name = "supplier_id",referencedColumnName = "id")
    private Supplier supplier;
    @ManyToOne @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product ;
    private int quantity;
    private double price;
    private double amount;
    private LocalDate orderAt;
    private LocalDate arrivesAt;
    private boolean isArrived;
    private Status status;
}
