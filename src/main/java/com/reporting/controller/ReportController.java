package com.reporting.controller;

import com.reporting.model.Report;
import com.reporting.model.dto.ReportDTO;
import com.reporting.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/report")
public class ReportController extends BaseController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportDTO> create(@RequestBody ReportDTO dto) {
        Report report = reportService.create(dto);
        ReportDTO result = report.toDTO(report);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
