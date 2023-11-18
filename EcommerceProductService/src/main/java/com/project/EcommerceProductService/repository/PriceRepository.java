package com.project.EcommerceProductService.repository;

import com.project.EcommerceProductService.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
}