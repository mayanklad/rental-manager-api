package com.rentalmanager.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String full_name;

    @Column(unique = true)
    private String email;

    private String phone;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

//    TODO: Bidirectional
//    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Lease> leases = new ArrayList<>();

//    TODO: Bidirectional
//    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MaintenanceRequest> maintenanceRequests = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
