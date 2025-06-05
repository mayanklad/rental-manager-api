package com.rentalmanager.api.controller;

import com.rentalmanager.api.dto.PropertyReqDTO;
import com.rentalmanager.api.dto.PropertyResDTO;
import com.rentalmanager.api.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<PropertyResDTO> getAll() {
        return propertyService.getAll();
    }

    @GetMapping("/{id}")
    public PropertyResDTO getById(@PathVariable long id) {
        return propertyService.getById(id);
    }

    @PostMapping
    public PropertyResDTO create(@Valid @RequestBody PropertyReqDTO propertyReqDTO) {
        return propertyService.create(propertyReqDTO);
    }

    @PutMapping("/{id}")
    public PropertyResDTO update(@PathVariable long id, @RequestBody PropertyReqDTO propertyReqDTO) {
        return propertyService.update(id, propertyReqDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        propertyService.delete(id);
    }
}
