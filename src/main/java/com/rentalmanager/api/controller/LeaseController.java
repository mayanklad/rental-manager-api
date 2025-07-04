package com.rentalmanager.api.controller;

import com.rentalmanager.api.dto.LeaseReqDto;
import com.rentalmanager.api.dto.LeaseResDto;
import com.rentalmanager.api.service.LeaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lease")
public class LeaseController {
    @Autowired
    private LeaseService leaseService;

    @GetMapping
    public List<LeaseResDto> getAll() {
        return leaseService.getAll();
    }

    @GetMapping("/{id}")
    public LeaseResDto getById(@PathVariable Long id) {
        return leaseService.getById(id);
    }

    @PostMapping
    public LeaseResDto create(@Valid @RequestBody LeaseReqDto leaseReqDto) {
        return leaseService.create(leaseReqDto);
    }

    //    TODO: Validation for update
    @PutMapping("/{id}")
    public LeaseResDto update(@PathVariable Long id, @RequestBody LeaseReqDto leaseReqDto) {
        return leaseService.update(id, leaseReqDto);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable Long id) {
        leaseService.delete(id);
    }
}
