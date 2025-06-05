package com.rentalmanager.api.service;

import com.rentalmanager.api.dto.PropertyReqDto;
import com.rentalmanager.api.dto.PropertyResDto;
import com.rentalmanager.api.entity.Property;
import com.rentalmanager.api.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepo propertyRepo;

    public PropertyResDto create(PropertyReqDto propertyReqDto) {
        return entityToResDto(propertyRepo.save(reqDTOToEntity(propertyReqDto)));
    }

    public List<PropertyResDto> getAll() {
        return propertyRepo.findAll()
                .stream()
                .map(this::entityToResDto)
                .toList();
    }

    public PropertyResDto getById(long id) {
        return entityToResDto(
                propertyRepo.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Property not found!"))
        );
    }

    public PropertyResDto update(long id, PropertyReqDto propertyReqDto) {
        Property property = propertyRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Property not found"));

        if (propertyReqDto.getName() != null && !propertyReqDto.getName().isBlank()) {
            property.setName(propertyReqDto.getName());
        }
        if (propertyReqDto.getAddress() != null && !propertyReqDto.getAddress().isBlank()) {
            property.setAddress(propertyReqDto.getAddress());
        }
        if (propertyReqDto.getType() != null && !propertyReqDto.getType().isBlank()) {
            property.setType(propertyReqDto.getType());
        }
        if (propertyReqDto.getDescription() != null) {
            property.setDescription(propertyReqDto.getDescription());
        }

        return entityToResDto(propertyRepo.save(property));
    }

    public void delete(long id) {
        propertyRepo.deleteById(id);
    }

    private PropertyResDto entityToResDto(Property property) {
        return new PropertyResDto(
                property.getId(),
                property.getName(),
                property.getAddress(),
                property.getType(),
                property.getDescription(),
                property.getCreated_at()
        );
    }

    private Property reqDTOToEntity(PropertyReqDto propertyReqDto) {
        Property property = new Property();
        property.setName(propertyReqDto.getName());
        property.setAddress(propertyReqDto.getAddress());
        property.setType(propertyReqDto.getType());
        property.setDescription(propertyReqDto.getDescription());

        return property;
    }
}
