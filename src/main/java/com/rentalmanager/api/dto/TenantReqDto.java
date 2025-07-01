package com.rentalmanager.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantReqDto {
    @NotBlank
    private String full_name;
    @Email
    private String email;
    @NotBlank
    private String phone;
    private List<Long> lease_ids;
}
