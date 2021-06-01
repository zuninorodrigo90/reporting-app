package com.reporting.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class ReportDTO {
    private Long id;
    private String name;
    private Boolean isPrivate;
    private String description;
    private Set<String> kpis;
}
