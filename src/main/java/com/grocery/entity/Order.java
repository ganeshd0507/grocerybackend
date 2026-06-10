package com.grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private String id;

    @Column(name = "order_date")
    private LocalDateTime date;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
//    private List<OrderItem> items;

    @Transient
    private List<OrderItem> items;

    private double subtotal;
    private double delivery;
    private double discount;
    private double total;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String slot;

    @PrePersist
    protected void onCreate() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
    }
}
