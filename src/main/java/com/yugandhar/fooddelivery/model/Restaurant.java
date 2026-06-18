package com.yugandhar.fooddelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column
    private String logoUrl;

    @Column
    private String bannerImageUrl;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false, length = 10)
    private String zipCode;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String websiteUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CuisineType cuisineType;

    @Column(nullable = false)
    private Double averageRating = 0.0;

    @Column(nullable = false)
    private Integer totalReviews = 0;

    @Column(nullable = false)
    private Double deliveryFee = 0.0;

    @Column(nullable = false)
    private Double minimumOrderAmount = 0.0;

    @Column(nullable = false)
    private Integer estimatedDeliveryTimeMin = 25;

    @Column(nullable = false)
    private Integer estimatedDeliveryTimeMax = 45;

    @Column
    private Double freeDeliveryAboveAmount;

    @Column(nullable = false)
    private Boolean isOpen = false;

    @Column(nullable = false)
    private Boolean isPaused = false;

    @Column(nullable = false)
    private Boolean isFeatured = false;

    @Column
    private LocalTime openingTime;

    @Column
    private LocalTime closingTime;

    @Column(nullable = false)
    private Boolean supportsScheduledOrders = false;

    @Column(nullable = false)
    private Boolean contactlessDelivery = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

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

    public enum CuisineType {
        AMERICAN, PIZZA, BURGER, CHINESE, INDIAN,
        MEXICAN, ITALIAN, JAPANESE, SUSHI, THAI,
        MEDITERRANEAN, VEGAN, VEGETARIAN, SEAFOOD,
        BBQ, SANDWICHES, SALADS, BREAKFAST, DESSERTS,
        COFFEE, GROCERY, ALCOHOL, CONVENIENCE
    }
}
