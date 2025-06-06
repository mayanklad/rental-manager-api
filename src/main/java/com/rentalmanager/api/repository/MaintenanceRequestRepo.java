package com.rentalmanager.api.repository;

import com.rentalmanager.api.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRequestRepo extends JpaRepository<MaintenanceRequest, Long> {
}
