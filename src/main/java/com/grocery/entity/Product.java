package com.grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    private double price;

    @Column(name = "old_price")
    private double oldPrice;

    private double discount;

    private String weight;

    private double rating;

    @Column(name = "reviews_count")
    private int reviewsCount;

    @Column(columnDefinition = "TEXT")
    private String image;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url", columnDefinition = "TEXT")
    private List<String> images;

    @Column(name = "in_stock")
    private boolean inStock;

    private String eta;

    @Column(columnDefinition = "TEXT")
    private String description;
}
