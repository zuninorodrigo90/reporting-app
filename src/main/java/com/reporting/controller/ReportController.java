package com.reporting.controller;

import com.reporting.model.Report;
import com.reporting.model.dto.ReportDTO;
import com.reporting.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/report")
@CrossOrigin
public class ReportController extends BaseController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportDTO> create(@RequestBody ReportDTO dto) {
        Report report = reportService.create(dto);
        ReportDTO result = Report.toDTO(report);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDTO> update(@PathVariable Long id, @RequestBody ReportDTO dto) {
        Report report = reportService.update(id, dto);
        ReportDTO result = Report.toDTO(report);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public Page<ReportDTO> listAll(Pageable pageable) {
        Page<Report> reportsPage = reportService.getAll(pageable);
        long total = reportsPage.getTotalElements();
        return new PageImpl<>(reportsPage.stream().map(Report::toDTO).collect(Collectors.toList()), pageable, total);
    }


}
