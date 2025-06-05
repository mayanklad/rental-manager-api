package com.rentalmanager.api.repository;

import com.rentalmanager.api.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseRepo extends JpaRepository<Lease, Long> {
}
