package com.rentalmanager.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentPaymentReqDto {
    @NotNull
    private Long lease_id;
    @NotNull
    private LocalDate payment_date;
    @NotNull
    private Float amount;
    @NotBlank
    private String status;
}
