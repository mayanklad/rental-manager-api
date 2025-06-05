package com.rentalmanager.api.service;

import com.rentalmanager.api.dto.PropertyReqDTO;
import com.rentalmanager.api.dto.PropertyResDTO;
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

    public PropertyResDTO create(PropertyReqDTO propertyReqDTO) {
        return entityToResDTO(propertyRepo.save(reqDTOToEntity(propertyReqDTO)));
    }

    public List<PropertyResDTO> getAll() {
        return propertyRepo.findAll()
                .stream()
                .map(this::entityToResDTO)
                .toList();
    }

    public PropertyResDTO getById(long id) {
        return entityToResDTO(
                propertyRepo.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Property not found!"))
        );
    }

    public PropertyResDTO update(long id, PropertyReqDTO propertyReqDTO) {
        Property property = propertyRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Property not found"));

        if (propertyReqDTO.getName() != null && !propertyReqDTO.getName().isBlank()) {
            property.setName(propertyReqDTO.getName());
        }
        if (propertyReqDTO.getAddress() != null && !propertyReqDTO.getAddress().isBlank()) {
            property.setAddress(propertyReqDTO.getAddress());
        }
        if (propertyReqDTO.getType() != null && !propertyReqDTO.getType().isBlank()) {
            property.setType(propertyReqDTO.getType());
        }
        if (propertyReqDTO.getDescription() != null) {
            property.setDescription(propertyReqDTO.getDescription());
        }

        return entityToResDTO(propertyRepo.save(property));
    }

    public void delete(long id) {
        propertyRepo.deleteById(id);
    }

    private PropertyResDTO entityToResDTO(Property property) {
        return new PropertyResDTO(
                property.getId(),
                property.getName(),
                property.getAddress(),
                property.getType(),
                property.getDescription(),
                property.getCreated_at()
        );
    }

    private Property reqDTOToEntity(PropertyReqDTO propertyReqDTO) {
        Property property = new Property();
        property.setName(propertyReqDTO.getName());
        property.setAddress(propertyReqDTO.getAddress());
        property.setType(propertyReqDTO.getType());
        property.setDescription(propertyReqDTO.getDescription());

        return property;
    }
}
