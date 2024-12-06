package com.example.kourse.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SupplierRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Product product;
    private int quantity;
    private boolean fulfilled;
}
