package com.rentalmanager.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResDto {
    private long id;
    private String name;
    private String address;
    private String type;
    private String description;
    private LocalDateTime created_at;
}
