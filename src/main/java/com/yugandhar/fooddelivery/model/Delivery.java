package com.yugandhar.fooddelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status = DeliveryStatus.ASSIGNED;

    @Column
    private Double driverLatitude;

    @Column
    private Double driverLongitude;

    @Column
    private Double distanceMiles;

    @Column
    private LocalDateTime estimatedArrivalTime;

    @Column
    private Double driverEarnings;

    @Column
    private Double tipAmount;

    @Column
    private String deliveryPhotoUrl;

    @Column
    private String driverNotes;

    @Column
    private LocalDateTime assignedAt;

    @Column
    private LocalDateTime pickedUpAt;

    @Column
    private LocalDateTime deliveredAt;

    @PrePersist
    protected void onCreate() {
        assignedAt = LocalDateTime.now();
    }

    public enum DeliveryStatus {
        ASSIGNED,
        HEADING_TO_RESTAURANT,
        ARRIVED_AT_RESTAURANT,
        PICKED_UP,
        HEADING_TO_CUSTOMER,
        ARRIVED_AT_CUSTOMER,
        DELIVERED,
        FAILED
    }
}