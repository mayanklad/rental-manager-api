package com.rentalmanager.api.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaseReqDto {
    @NotNull
    private Long property_id;
    @NotNull
    @Size(min = 1, message = "Lease must have at least one tenant")
    private List<Long> tenant_ids;
    @NotNull
    private LocalDate start_date;
    @NotNull
    private LocalDate end_date;
    @NotNull
    private Float rent_amount;
}
