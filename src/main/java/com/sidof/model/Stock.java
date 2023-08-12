package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 17/05/2023
 * @Version v1.0
 * */

 /**
  * This class properie show the diffrend type of article storage in company.
 * @parame openingStock The inial or last stock before new purchase.
 * @parame minimumStock .
 * @parame securityStock;
 * @parame alertStock;
 * @parame averageStock;
 * @parame finalStock;
 * @parame availableStock;
 * @parame outStock;
 */
 @Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Stock {
     @Id @SequenceGenerator(name = "sequence_id_stock",allocationSize = 1,sequenceName = "sequence_id_stock") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_stock")
    private Long id;
    private int openingStock;
    private int minimumStock;
    private int securityStock;
    private int alertStock;
    private int averageStock;
    private int finalStock;
    private int availableStock;
    private int outStock;
    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    private int quantity;


}
