package com.rentalmanager.api.service;

import com.rentalmanager.api.dto.RentPaymentReqDto;
import com.rentalmanager.api.dto.RentPaymentResDto;
import com.rentalmanager.api.entity.RentPayment;
import com.rentalmanager.api.repository.LeaseRepo;
import com.rentalmanager.api.repository.RentPaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentPaymentService {
    @Autowired
    private RentPaymentRepo rentPaymentRepo;

    @Autowired
    private LeaseRepo leaseRepo;

    public List<RentPaymentResDto> getAll() {
        return rentPaymentRepo.findAll()
                .stream()
                .map(this::entityToResDto)
                .toList();
    }

    public RentPaymentResDto getById(Long id) {
        return entityToResDto(
                rentPaymentRepo.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Rent Payment not found!"))
        );
    }

    public RentPaymentResDto create(RentPaymentReqDto rentPaymentReqDto) {
        return entityToResDto(
                rentPaymentRepo.save(reqDtoToEntity(rentPaymentReqDto))
        );
    }

    public RentPaymentResDto update(Long id, RentPaymentReqDto rentPaymentReqDto) {
        RentPayment rentPayment = rentPaymentRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Rent Payment not found!"));

        if (rentPaymentReqDto.getLease_id() != null) {
            rentPayment.setLease(
                    leaseRepo.findById(rentPaymentReqDto.getLease_id())
                            .orElseThrow(() -> new NoSuchElementException("Lease not found!"))
            );
        }
        if (rentPaymentReqDto.getPayment_date() != null) {
            rentPayment.setPayment_date(rentPaymentReqDto.getPayment_date());
        }
        if (rentPaymentReqDto.getAmount() != null) {
            rentPayment.setAmount(rentPaymentReqDto.getAmount());
        }
        if (rentPaymentReqDto.getStatus() != null) {
            rentPayment.setStatus(rentPaymentReqDto.getStatus());
        }

        return entityToResDto(rentPaymentRepo.save(rentPayment));
    }

    public void delete(Long id) {
        rentPaymentRepo.deleteById(id);
    }

    private RentPaymentResDto entityToResDto(RentPayment rentPayment) {
        return new RentPaymentResDto(
                rentPayment.getId(),
                rentPayment.getLease().getId(),
                rentPayment.getPayment_date(),
                rentPayment.getAmount(),
                rentPayment.getStatus(),
                rentPayment.getCreated_at()
        );
    }

    private RentPayment reqDtoToEntity(RentPaymentReqDto rentPaymentReqDto) {
        RentPayment rentPayment = new RentPayment();
        rentPayment.setLease(
                leaseRepo.findById(rentPaymentReqDto.getLease_id())
                        .orElseThrow(() -> new NoSuchElementException("Lease not found!"))
        );
        rentPayment.setPayment_date(rentPaymentReqDto.getPayment_date());
        rentPayment.setAmount(rentPaymentReqDto.getAmount());
        rentPayment.setStatus(rentPaymentReqDto.getStatus());

        return rentPayment;
    }
}
