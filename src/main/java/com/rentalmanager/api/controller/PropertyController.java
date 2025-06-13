package com.rentalmanager.api.controller;

import com.rentalmanager.api.dto.PropertyReqDto;
import com.rentalmanager.api.dto.PropertyResDto;
import com.rentalmanager.api.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<PropertyResDto> getAll() {
        return propertyService.getAll();
    }

    @GetMapping("/{id}")
    public PropertyResDto getById(@PathVariable Long id) {
        return propertyService.getById(id);
    }

    @PostMapping
    public PropertyResDto create(@Valid @RequestBody PropertyReqDto propertyReqDto) {
        return propertyService.create(propertyReqDto);
    }

//    TODO: Validation for update
    @PutMapping("/{id}")
    public PropertyResDto update(@PathVariable Long id, @RequestBody PropertyReqDto propertyReqDto) {
        return propertyService.update(id, propertyReqDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        propertyService.delete(id);
    }
}
