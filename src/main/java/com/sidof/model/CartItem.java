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
 * @Since 06/11/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @SequenceGenerator(name = "sequence_id_cartItem",allocationSize = 1,sequenceName = "sequence_id_cartItem") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_cartItem")
    private Long id;


}
