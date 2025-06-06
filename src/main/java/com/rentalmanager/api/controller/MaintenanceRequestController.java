package com.rentalmanager.api.controller;

import com.rentalmanager.api.dto.MaintenanceRequestReqDto;
import com.rentalmanager.api.dto.MaintenanceRequestResDto;
import com.rentalmanager.api.service.MaintenanceRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenanceRequest")
public class MaintenanceRequestController {
    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @GetMapping
    public List<MaintenanceRequestResDto> getAll() {
        return maintenanceRequestService.getAll();
    }

    @GetMapping("/{id}")
    public MaintenanceRequestResDto getById(@PathVariable Long id) {
        return maintenanceRequestService.getById(id);
    }

    @PostMapping
    public MaintenanceRequestResDto create(@Valid @RequestBody MaintenanceRequestReqDto maintenanceRequestReqDto) {
        return maintenanceRequestService.create(maintenanceRequestReqDto);
    }

    //    TODO: Validation for update
    @PutMapping("/{id}")
    public MaintenanceRequestResDto update(@PathVariable Long id, @RequestBody MaintenanceRequestReqDto maintenanceRequestReqDto) {
        return maintenanceRequestService.update(id, maintenanceRequestReqDto);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable Long id) {
        maintenanceRequestService.delete(id);
    }
}
