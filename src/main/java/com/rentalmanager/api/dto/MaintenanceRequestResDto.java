package com.rentalmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceRequestResDto {
    private Long id;
    private Long tenant_id;
    private Long property_id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime created_at;
}
