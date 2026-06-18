package com.yugandhar.fooddelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$")
    @Column(unique = true)
    private String phoneNumber;

    @Column
    private String profilePictureUrl;

    @Column
    private String streetAddress;

    @Column
    private String city;

    @Column
    private String state;

    @Column(length = 10)
    private String zipCode;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean isPremiumMember = false;

    @Column(unique = true)
    private String referralCode;

    @Column(nullable = false)
    private Double walletBalance = 0.0;

    @Column(nullable = false)
    private Integer totalOrdersPlaced = 0;

    @Column(nullable = false)
    private Integer loyaltyPoints = 0;

    @Enumerated(EnumType.STRING)
    @Column
    private PaymentMethod preferredPaymentMethod;

    @Column(nullable = false)
    private Boolean notificationsEnabled = true;

    @Column(nullable = false)
    private Boolean smsUpdatesEnabled = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Column(nullable = false)
    private Boolean phoneVerified = false;

    @Column
    private LocalDateTime lastLoginAt;

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

    public enum Role {
        CUSTOMER,
        RESTAURANT_OWNER,
        DRIVER,
        ADMIN
    }

    public enum AccountStatus {
        ACTIVE,
        SUSPENDED,
        BANNED,
        PENDING_VERIFICATION
    }

    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        APPLE_PAY,
        GOOGLE_PAY,
        PAYPAL,
        WALLET_BALANCE,
        CASH_ON_DELIVERY
    }
}