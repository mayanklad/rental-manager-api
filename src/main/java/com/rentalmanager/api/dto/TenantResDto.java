package com.rentalmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantResDto {
    private Long id;
    private String full_name;
    private String email;
    private String phone;
    private List<Long> lease_ids;
    private LocalDateTime created_at;
}
