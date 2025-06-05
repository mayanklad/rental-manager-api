package com.rentalmanager.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyReqDto {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String type;
    private String description;
}
