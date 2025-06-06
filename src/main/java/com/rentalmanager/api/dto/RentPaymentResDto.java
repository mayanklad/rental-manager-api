package com.rentalmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentPaymentResDto {
    private Long id;
    private Long lease_id;
    private LocalDate payment_date;
    private Float amount;
    private String status;
    private LocalDateTime created_at;
}
