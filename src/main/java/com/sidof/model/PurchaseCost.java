package com.sidof.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 17/05/2023
 * @Version v1.0
 */

/**
 * @parame transport It's cost that company support when the buy article.
 * @parame call    It's the costbthat Company use to call supplier.
 * @parame lost steal or lost article support by company.
 * @parame parkaging for parkaging article or product support by company.
 * @parame taxe for purchase.
 * @parame security for keeping article.
 * @parame stoarage = transport + call + lost + parkaging.
 */
@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class PurchaseCost {
    @Id @SequenceGenerator(name = "sequence_id_purchase_cost",allocationSize = 1,sequenceName = "sequence_id_purchase_cost") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_purchase_cost")
    private Long id;
    private double transport;
    private double call;
    private double lost;
    private double packaging;
    private double courant;
    private double security;
    private double taxe;
    private double storage;

}
