package com.rentalmanager.api.service;

import com.rentalmanager.api.dto.TenantReqDto;
import com.rentalmanager.api.dto.TenantResDto;
import com.rentalmanager.api.entity.Lease;
import com.rentalmanager.api.entity.Tenant;
import com.rentalmanager.api.repository.LeaseRepo;
import com.rentalmanager.api.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TenantService {
    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private LeaseRepo leaseRepo;

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

//    TODO: Check if lease(s) exists or not, throw or handle exception if not
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
        if (tenantReqDto.getLease_ids() != null) {
            List<Lease> leases = leaseRepo.findAllById(tenantReqDto.getLease_ids());
            tenant.setLeases(leases);

            // For bidirectional consistency
            leases.forEach(lease -> {
                if (!lease.getTenants().contains(tenant)) {
                    lease.getTenants().add(tenant);
                }
            });
        }

        return entityToResDto(tenantRepo.save(tenant));
    }

    public void delete(Long id) {
        Tenant tenant = tenantRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tenant not found!"));

        // Remove tenant from associated leases
        List<Lease> leasesToDelete = new ArrayList<>();
        for (Lease lease : tenant.getLeases()) {
            lease.getTenants().remove(tenant);
            if (lease.getTenants().isEmpty()) {
                leasesToDelete.add(lease); // delete lease with no tenant
            }
        }

        tenantRepo.delete(tenant);
        leaseRepo.deleteAll(leasesToDelete);
    }

    private Tenant reqDtoToEntity(TenantReqDto tenantReqDto) {
        Tenant tenant = new Tenant();
        tenant.setFull_name(tenantReqDto.getFull_name());
        tenant.setEmail(tenantReqDto.getEmail());
        tenant.setPhone(tenantReqDto.getPhone());

        if (tenantReqDto.getLease_ids() != null && !tenantReqDto.getLease_ids().isEmpty()) {
            List<Lease> leases = leaseRepo.findAllById(tenantReqDto.getLease_ids());
            tenant.setLeases(leases);

            // Maintain bidirectional
            leases.forEach(lease -> lease.getTenants().add(tenant));
        }

        return tenant;
    }

    private TenantResDto entityToResDto(Tenant tenant) {
        List<Long> leaseIds = tenant.getLeases() != null
                ? tenant.getLeases().stream().map(Lease::getId).toList()
                : Collections.emptyList();

        return new TenantResDto(
                tenant.getId(),
                tenant.getFull_name(),
                tenant.getEmail(),
                tenant.getPhone(),
                leaseIds,
                tenant.getCreated_at()
        );
    }
}
