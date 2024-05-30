package com.thecoders.cartunnbackend.orders.domain.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "entry_date")
    private String entryDate;

    @Column(name = "exit_date")
    private String exitDate;

    private String status;
}
