package com.rentalmanager.api.service;

import com.rentalmanager.api.dto.MaintenanceRequestReqDto;
import com.rentalmanager.api.dto.MaintenanceRequestResDto;
import com.rentalmanager.api.entity.MaintenanceRequest;
import com.rentalmanager.api.repository.MaintenanceRequestRepo;
import com.rentalmanager.api.repository.PropertyRepo;
import com.rentalmanager.api.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MaintenanceRequestService {
    @Autowired
    private MaintenanceRequestRepo maintenanceRequestRepo;

    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private PropertyRepo propertyRepo;

    public List<MaintenanceRequestResDto> getAll() {
        return maintenanceRequestRepo.findAll()
                .stream()
                .map(this::entityToResDto)
                .toList();
    }

    public MaintenanceRequestResDto getById(Long id) {
        return entityToResDto(
                maintenanceRequestRepo.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Maintenance Request not found!"))
        );
    }

    public MaintenanceRequestResDto create(MaintenanceRequestReqDto maintenanceRequestReqDto) {
        return entityToResDto(
                maintenanceRequestRepo.save(reqDtoToEntity(maintenanceRequestReqDto))
        );
    }

    public MaintenanceRequestResDto update(Long id, MaintenanceRequestReqDto maintenanceRequestReqDto) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Maintenance Request not found!"));

        if (maintenanceRequestReqDto.getTenant_id() != null) {
            maintenanceRequest.setTenant(
                    tenantRepo.findById(maintenanceRequestReqDto.getTenant_id())
                            .orElseThrow(() -> new NoSuchElementException("Tenant not found!"))
            );
        }
        if (maintenanceRequestReqDto.getProperty_id() != null) {
            maintenanceRequest.setProperty(
                    propertyRepo.findById(maintenanceRequestReqDto.getProperty_id())
                            .orElseThrow(() -> new NoSuchElementException("Property not found!"))
            );
        }
        if (maintenanceRequestReqDto.getTitle() != null) {
            maintenanceRequest.setTitle(maintenanceRequestReqDto.getTitle());
        }
        if (maintenanceRequestReqDto.getDescription() != null) {
            maintenanceRequest.setDescription(maintenanceRequestReqDto.getDescription());
        }
        if (maintenanceRequestReqDto.getStatus() != null) {
            maintenanceRequest.setStatus(maintenanceRequestReqDto.getStatus());
        }

        return entityToResDto(maintenanceRequestRepo.save(maintenanceRequest));
    }

    public void delete(Long id) {
        maintenanceRequestRepo.deleteById(id);
    }

    private MaintenanceRequestResDto entityToResDto(MaintenanceRequest maintenanceRequest) {
        return new MaintenanceRequestResDto(
                maintenanceRequest.getId(),
                maintenanceRequest.getTenant().getId(),
                maintenanceRequest.getProperty().getId(),
                maintenanceRequest.getTitle(),
                maintenanceRequest.getDescription(),
                maintenanceRequest.getStatus(),
                maintenanceRequest.getCreated_at()
        );
    }

    private MaintenanceRequest reqDtoToEntity(MaintenanceRequestReqDto maintenanceRequestReqDto) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setTenant(
                tenantRepo.findById(maintenanceRequestReqDto.getTenant_id())
                        .orElseThrow(() -> new NoSuchElementException("Tenant not found!"))
        );
        maintenanceRequest.setProperty(
                propertyRepo.findById(maintenanceRequestReqDto.getProperty_id())
                        .orElseThrow(() -> new NoSuchElementException("Property not found!"))
        );
        maintenanceRequest.setTitle(maintenanceRequestReqDto.getTitle());
        maintenanceRequest.setDescription(maintenanceRequestReqDto.getDescription());
        maintenanceRequest.setStatus(maintenanceRequestReqDto.getStatus());

        return maintenanceRequest;
    }
}
