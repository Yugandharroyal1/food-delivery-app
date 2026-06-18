package com.yugandhar.fooddelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Double price;

    @Column
    private Double discountedPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodCategory category;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @Column(nullable = false)
    private Boolean isPopular = false;

    @Column(nullable = false)
    private Boolean isFeatured = false;

    @Column(nullable = false)
    private Boolean isVegetarian = false;

    @Column(nullable = false)
    private Boolean isVegan = false;

    @Column(nullable = false)
    private Boolean isGlutenFree = false;

    @Column(nullable = false)
    private Boolean isHalal = false;

    @Column(nullable = false)
    private Boolean isKosher = false;

    @Column(nullable = false)
    private Boolean isSpicy = false;

    @Column
    private Integer calories;

    @Column
    private Integer prepTimeMinutes;

    @Column(nullable = false)
    private Integer totalOrdered = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum FoodCategory {
        APPETIZERS, SOUPS, SALADS, BURGERS,
        SANDWICHES, PIZZA, PASTA, MAINS,
        SIDES, DESSERTS, DRINKS, ALCOHOL,
        BREAKFAST, KIDS_MENU, COMBO_MEALS,
        VEGAN_SPECIALS, HEALTHY_OPTIONS
    }
}