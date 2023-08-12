package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 17/05/2023
 * @Version v1.0
 */
@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Product {
    @Id @SequenceGenerator(name = "sequence_id_product",allocationSize = 1,sequenceName = "sequence_id_product") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_product")
    private Long id;
    private String name;
    private  String description;
    private double price;
    @Column(unique = true)
    private String code;
    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = ALL)
    private List<Purchase>purchases = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Sale>sales = new ArrayList<>();
    @OneToOne(mappedBy = "product")
    private Stock stock;
}
