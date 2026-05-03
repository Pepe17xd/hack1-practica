package com.example.oreo.repository;


import com.example.oreo.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
public interface SaleRepository extends JpaRepository<Sale, String> {

    List<Sale> findBySoldAtBetween(Instant from, Instant to);

    List<Sale> findByBranchAndSoldAtBetween(String branch, Instant from, Instant to);

    List<Sale> findByBranch(String branch);
}