package com.rentalmanager.api.service;

import com.rentalmanager.api.dto.LeaseReqDto;
import com.rentalmanager.api.dto.LeaseResDto;
import com.rentalmanager.api.entity.Lease;
import com.rentalmanager.api.entity.Tenant;
import com.rentalmanager.api.repository.LeaseRepo;
import com.rentalmanager.api.repository.PropertyRepo;
import com.rentalmanager.api.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LeaseService {
    @Autowired
    private LeaseRepo leaseRepo;

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private TenantRepo tenantRepo;

    public List<LeaseResDto> getAll() {
        return leaseRepo.findAll()
                .stream()
                .map(this::entityToResDto)
                .toList();
    }

    public LeaseResDto getById(Long id) {
        return entityToResDto(
                leaseRepo.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Lease not found!"))
        );
    }

    public LeaseResDto create(LeaseReqDto leaseReqDto) {
//        TODO: check if tenant(s) exists or not and throw handle exception if not
        return entityToResDto(
                leaseRepo.save(reqDtoToEntity(leaseReqDto))
        );
    }

    public LeaseResDto update(Long id, LeaseReqDto leaseReqDto) {
        Lease lease = leaseRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Lease not found!"));

        if (leaseReqDto.getProperty_id() != null) {
            lease.setProperty(
                    propertyRepo.findById(leaseReqDto.getProperty_id())
                            .orElseThrow(() -> new NoSuchElementException("Property not found!"))
            );
        }
        if (leaseReqDto.getTenant_ids() != null && !leaseReqDto.getTenant_ids().isEmpty()) {
            List<Tenant> tenants = tenantRepo.findAllById(leaseReqDto.getTenant_ids());
            lease.setTenants(tenants);

            // for bidirectional consistency
            tenants.forEach(t -> {
                if (!t.getLeases().contains(lease)) {
                    t.getLeases().add(lease);
                }
            });
        }
        if (leaseReqDto.getStart_date() != null) {
            lease.setStart_date(leaseReqDto.getStart_date());
        }
        if (leaseReqDto.getEnd_date() != null) {
            lease.setEnd_date(leaseReqDto.getEnd_date());
        }
        if (leaseReqDto.getRent_amount() != null) {
            lease.setRent_amount(leaseReqDto.getRent_amount());
        }

        return entityToResDto(leaseRepo.save(lease));
    }

    public void delete(Long id) {
        leaseRepo.deleteById(id);
    }

    private LeaseResDto entityToResDto(Lease lease) {
        List<Long> tenantIds = lease.getTenants()
                .stream()
                .map(Tenant::getId)
                .toList();

        return new LeaseResDto(
                lease.getId(),
                lease.getProperty().getId(),
                tenantIds,
                lease.getStart_date(),
                lease.getEnd_date(),
                lease.getRent_amount(),
                lease.getCreated_at()
        );
    }

    private Lease reqDtoToEntity(LeaseReqDto leaseReqDto) {
        Lease lease = new Lease();
        lease.setProperty(
                propertyRepo.findById(leaseReqDto.getProperty_id())
                        .orElseThrow(() -> new NoSuchElementException("Property not found!"))
        );

        List<Tenant> tenants = tenantRepo.findAllById(leaseReqDto.getTenant_ids());
        lease.setTenants(tenants);
        // for bidirectional relationship
        tenants.forEach(t -> t.getLeases().add(lease));

        lease.setStart_date(leaseReqDto.getStart_date());
        lease.setEnd_date(leaseReqDto.getEnd_date());
        lease.setRent_amount(leaseReqDto.getRent_amount());

        return lease;
    }
}
