package com.rentalmanager.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceRequestReqDto {
    @NotNull
    private Long tenant_id;
    @NotNull
    private Long property_id;
    @NotBlank
    private String title;
    private String description;
    @NotBlank
    private String status;
}
