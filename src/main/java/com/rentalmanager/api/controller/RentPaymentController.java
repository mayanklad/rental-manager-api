package com.rentalmanager.api.controller;

import com.rentalmanager.api.dto.RentPaymentReqDto;
import com.rentalmanager.api.dto.RentPaymentResDto;
import com.rentalmanager.api.service.RentPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentPayment")
public class RentPaymentController {
    @Autowired
    private RentPaymentService rentPaymentService;

    @GetMapping
    public List<RentPaymentResDto> getAll() {
        return rentPaymentService.getAll();
    }

    @GetMapping("/{id}")
    public RentPaymentResDto getById(@PathVariable Long id) {
        return rentPaymentService.getById(id);
    }

    @PostMapping
    public RentPaymentResDto create(@Valid @RequestBody RentPaymentReqDto rentPaymentReqDto) {
        return rentPaymentService.create(rentPaymentReqDto);
    }

    //    TODO: Validation for update
    @PutMapping("/{id}")
    public RentPaymentResDto update(@PathVariable Long id, @RequestBody RentPaymentReqDto rentPaymentReqDto) {
        return rentPaymentService.update(id, rentPaymentReqDto);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable Long id) {
        rentPaymentService.delete(id);
    }
}
