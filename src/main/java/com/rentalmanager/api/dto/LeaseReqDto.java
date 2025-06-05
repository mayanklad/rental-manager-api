package com.rentalmanager.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseReqDto {
    @NotNull
    private Long property_id;
    @NotNull
    private Long tenant_id;
    @NotNull
    private LocalDate start_date;
    @NotNull
    private LocalDate end_date;
    @NotNull
    private Float rent_amount;
}
