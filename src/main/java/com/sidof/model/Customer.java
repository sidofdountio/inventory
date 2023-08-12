package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

/**
 * @Author sidof
 * @Since 17/05/2023
 * @Version v1.0
 */
@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Customer {
    @Id @SequenceGenerator(name = "sequence_id_customer",allocationSize = 1,sequenceName = "sequence_id_customer") @GeneratedValue(strategy = SEQUENCE,generator = "sequence_id_customer")
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String phone;
    private String  address;
    @Column(unique = true,nullable = false)
        private String nationalIdCard;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Sale> sales = new ArrayList<>();

}
