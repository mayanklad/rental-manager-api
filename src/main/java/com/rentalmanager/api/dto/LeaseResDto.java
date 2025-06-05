package com.rentalmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseResDto {
    private Long id;
    private Long property_id;
    private Long tenant_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private Float rent_amount;
    private LocalDateTime created_at;
}
