package com.rentalmanager.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    private LocalDate start_date;
    private LocalDate end_date;
    private float rent_amount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

//    TODO: Bidirectional
//    @OneToMany(mappedBy = "lease", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<RentPayment> rentPayments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
