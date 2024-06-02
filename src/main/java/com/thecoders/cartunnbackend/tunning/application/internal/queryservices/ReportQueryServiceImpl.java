package com.thecoders.cartunnbackend.tunning.application.internal.queryservices;

import com.thecoders.cartunnbackend.tunning.domain.model.aggregates.Report;
import com.thecoders.cartunnbackend.tunning.domain.model.queries.GetAllReportsQuery;
import com.thecoders.cartunnbackend.tunning.domain.model.queries.GetReportByIdQuery;
import com.thecoders.cartunnbackend.tunning.domain.services.ReportQueryService;
import com.thecoders.cartunnbackend.tunning.infrastructure.persitence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {
    private final ReportRepository reportRepository;
    public ReportQueryServiceImpl(ReportRepository reportRepository)
    {this.reportRepository = reportRepository;}
    @Override
    public Optional<Report> handle(GetReportByIdQuery query) {
        return reportRepository.findById(query.reportId());
    }

    @Override
    public List<Report> handle(GetAllReportsQuery query) {
        return reportRepository.findAll();
    }
}
