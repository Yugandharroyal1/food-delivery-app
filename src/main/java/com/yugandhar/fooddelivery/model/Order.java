package com.yugandhar.fooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private User driver;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PLACED;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double deliveryFee;

    @Column(nullable = false)
    private Double serviceFee;

    @Column(nullable = false)
    private Double taxAmount;

    @Column(nullable = false)
    private Double tipAmount = 0.0;

    @Column(nullable = false)
    private Double discountAmount = 0.0;

    @Column(nullable = false)
    private Double totalAmount;

    @Column
    private String promoCode;

    @Column(nullable = false)
    private Double walletCreditsUsed = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private User.PaymentMethod paymentMethod;

    @Column
    private String stripePaymentIntentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private String deliveryCity;

    @Column(nullable = false)
    private String deliveryState;

    @Column(nullable = false)
    private String deliveryZipCode;

    @Column
    private Double deliveryLatitude;

    @Column
    private Double deliveryLongitude;

    @Column
    private String deliveryInstructions;

    @Column(nullable = false)
    private Boolean contactlessDelivery = false;

    @Column
    private LocalDateTime scheduledDeliveryTime;

    @Column
    private LocalDateTime estimatedDeliveryTime;

    @Column
    private LocalDateTime actualDeliveryTime;

    @Column
    private Double driverCurrentLatitude;

    @Column
    private Double driverCurrentLongitude;

    @Column
    private Integer customerRating;

    @Column
    private String customerReview;

    @Column
    private Integer driverRating;

    @Column
    private Boolean isRefunded = false;

    @Column
    private Double refundAmount;

    @Column
    private String refundReason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime placedAt;

    @Column
    private LocalDateTime confirmedAt;

    @Column
    private LocalDateTime preparingAt;

    @Column
    private LocalDateTime readyAt;

    @Column
    private LocalDateTime pickedUpAt;

    @Column
    private LocalDateTime deliveredAt;

    @PrePersist
    protected void onCreate() {
        placedAt = LocalDateTime.now();
    }

    public enum OrderStatus {
        PLACED,
        CONFIRMED,
        PREPARING,
        READY,
        DRIVER_ASSIGNED,
        PICKED_UP,
        ON_THE_WAY,
        DELIVERED,
        CANCELLED,
        REFUNDED
    }

    public enum PaymentStatus {
        PENDING,
        PAID,
        FAILED,
        REFUNDED,
        PARTIALLY_REFUNDED
    }
}