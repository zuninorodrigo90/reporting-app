package com.reporting.service;

import com.reporting.model.Kpi;
import com.reporting.repository.KpiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KpiService {
    private final KpiRepository repository;

    public KpiService(KpiRepository repository) {
        this.repository = repository;
    }

    public Page<Kpi> getAll(Pageable pageable) {
        log.debug("Getting all KPIs");
        return repository.findAll(pageable);
    }
}
