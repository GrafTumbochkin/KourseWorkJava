package com.example.kourse.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderDate;
    private LocalDate completionDate;

    @ManyToOne
    private User client;

    @ManyToOne
    private Product product;
    private int quantity;
}