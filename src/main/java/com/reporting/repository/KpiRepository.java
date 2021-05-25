package com.reporting.repository;

import com.reporting.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KpiRepository extends JpaRepository<Kpi, Long> {
    Optional<Kpi> findByName(String name);
}
