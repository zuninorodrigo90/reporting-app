package com.reporting.service;

import com.reporting.model.Kpi;
import com.reporting.model.Report;
import com.reporting.model.dto.ReportDTO;
import com.reporting.repository.KpiRepository;
import com.reporting.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;


@Service
@Slf4j
public class ReportService {
    private final ReportRepository repository;
    private final KpiRepository kpiRepository;

    public ReportService(ReportRepository repository, KpiRepository kpiRepository) {
        this.repository = repository;
        this.kpiRepository = kpiRepository;
    }

    @Transactional
    public Report create(ReportDTO dto) {
        log.debug("Creating Report: [{}]", dto);
        if (dto.getName() == null) {
            throw new IllegalArgumentException(String.format("[%s] is required and cannot bet null", dto.getName()));
        }

        Set<Kpi> kpis = new HashSet<>();
        if (!CollectionUtils.isEmpty(dto.getKpis())) {
            dto.getKpis().forEach(n -> {
                        Kpi kpi = kpiRepository.findByName(n).orElseThrow(EntityNotFoundException::new);
                        kpis.add(kpi);
                    }
            );
        }

        Report report = Report.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .isPrivate(dto.getIsPrivate())
                .kpis(kpis).build();
        Report reportCreated = repository.save(report);
        log.debug("Report created: [{}]", reportCreated);
        return reportCreated;
    }
}
