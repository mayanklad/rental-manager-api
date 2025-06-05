package com.rentalmanager.api.service;

import com.rentalmanager.api.dto.TenantReqDto;
import com.rentalmanager.api.dto.TenantResDto;
import com.rentalmanager.api.entity.Tenant;
import com.rentalmanager.api.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TenantService {
    @Autowired
    private TenantRepo tenantRepo;

    public List<TenantResDto> getAll() {
        return tenantRepo.findAll()
                .stream()
                .map(this::entityToResDto)
                .toList();
    }

    public TenantResDto getById(Long id) {
        return entityToResDto(
                tenantRepo.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Tenant not found!"))
        );
    }

    public TenantResDto create(TenantReqDto tenantReqDto) {
        return entityToResDto(
                tenantRepo.save(reqDtoToEntity(tenantReqDto))
        );
    }

    public TenantResDto update(Long id, TenantReqDto tenantReqDto) {
        Tenant tenant = tenantRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Tenant not found!"));

        if (tenantReqDto.getFull_name() != null && !tenantReqDto.getFull_name().isBlank()) {
            tenant.setFull_name(tenantReqDto.getFull_name());
        }
        if (tenantReqDto.getEmail() != null && !tenantReqDto.getEmail().isBlank()) {
            tenant.setEmail(tenantReqDto.getEmail());
        }
        if (tenantReqDto.getPhone() != null && !tenantReqDto.getPhone().isBlank()) {
            tenant.setPhone(tenantReqDto.getPhone());
        }

        return entityToResDto(tenantRepo.save(tenant));
    }

    public void delete(Long id) {
        tenantRepo.deleteById(id);
    }

    private Tenant reqDtoToEntity(TenantReqDto tenantReqDto) {
        Tenant tenant = new Tenant();
        tenant.setFull_name(tenantReqDto.getFull_name());
        tenant.setEmail(tenantReqDto.getEmail());
        tenant.setPhone(tenantReqDto.getPhone());

        return tenant;
    }

    private TenantResDto entityToResDto(Tenant tenant) {
        return new TenantResDto(
                tenant.getId(),
                tenant.getFull_name(),
                tenant.getEmail(),
                tenant.getPhone(),
                tenant.getCreated_at()
        );
    }
}
