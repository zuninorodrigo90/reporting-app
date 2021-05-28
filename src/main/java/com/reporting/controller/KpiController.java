package com.reporting.controller;

import com.reporting.model.Kpi;
import com.reporting.service.KpiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/kpi")
public class KpiController extends BaseController {

    private final KpiService kpiService;

    public KpiController(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @CrossOrigin
    @GetMapping("/list")
    public Page<Kpi> listAll(Pageable pageable) {
        return kpiService.getAll(pageable);
    }
}
