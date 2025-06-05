package com.rentalmanager.api.controller;

import com.rentalmanager.api.dto.TenantReqDto;
import com.rentalmanager.api.dto.TenantResDto;
import com.rentalmanager.api.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @GetMapping
    public List<TenantResDto> getAll() {
        return tenantService.getAll();
    }

    @GetMapping("/{id}")
    public TenantResDto getById(@PathVariable Long id) {
        return tenantService.getById(id);
    }

    @PostMapping
    public TenantResDto create(@Valid @RequestBody TenantReqDto tenantReqDto) {
        return tenantService.create(tenantReqDto);
    }

//    TODO: Validation for update
    @PutMapping("/{id}")
    public TenantResDto update(@PathVariable Long id, @RequestBody TenantReqDto tenantReqDto) {
        return tenantService.update(id, tenantReqDto);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable Long id) {
        tenantService.delete(id);
    }
}
