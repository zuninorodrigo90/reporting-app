package com.reporting.service;

import com.reporting.model.Kpi;
import com.reporting.model.Report;
import com.reporting.model.dto.ReportDTO;
import com.reporting.repository.KpiRepository;
import com.reporting.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;


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

    @Transactional
    public Report update(Long id, ReportDTO dto) {
        log.debug("Updating Report: [{}]", dto);
        Report report = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (StringUtils.isNotEmpty(dto.getName())) {
            report.setName(dto.getName());
        }
        if(StringUtils.isNotEmpty(dto.getDescription())){
            report.setDescription(dto.getDescription());
        }
        if (dto.getIsPrivate() != null) {
            report.setIsPrivate(dto.getIsPrivate());
        }
        if (!CollectionUtils.isEmpty(dto.getKpis())) {
            Set<Kpi> kpis = new HashSet<>();
            dto.getKpis().forEach(name -> {
                        Kpi kpi = kpiRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
                        kpis.add(kpi);
                    }
            );
            report.setKpis(kpis);
        }
        Report reportUpdated = repository.save(report);
        log.debug("Report updated: [{}]", reportUpdated);
        return reportUpdated;
    }

    public Page<Report> getAll(Pageable pageable) {
        log.debug("Getting all reports");
        return repository.findAll(pageable);
    }
}
