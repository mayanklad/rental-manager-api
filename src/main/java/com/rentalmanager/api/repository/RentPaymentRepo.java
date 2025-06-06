package com.rentalmanager.api.repository;

import com.rentalmanager.api.entity.RentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentPaymentRepo extends JpaRepository<RentPayment, Long> {
}
