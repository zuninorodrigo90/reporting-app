package com.reporting.model;

import com.reporting.model.dto.ReportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseEntity {
    private String name;
    private Boolean isPrivate;
    private String description;
    @ManyToMany
    private Set<Kpi> kpis;

    public ReportDTO toDTO(Report report) {
        return ReportDTO.builder()
                .name(report.getName())
                .description(report.getDescription())
                .isPrivate(report.getIsPrivate())
                .kpis(report.getKpis().stream().map(Kpi::getName).collect(toSet()))
                .build();

    }

}
