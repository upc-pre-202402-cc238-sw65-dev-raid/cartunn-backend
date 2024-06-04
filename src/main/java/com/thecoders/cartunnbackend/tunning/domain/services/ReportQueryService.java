package com.thecoders.cartunnbackend.tunning.domain.services;

import com.thecoders.cartunnbackend.tunning.domain.model.aggregates.Report;
import com.thecoders.cartunnbackend.tunning.domain.model.queries.GetAllReportsQuery;
import com.thecoders.cartunnbackend.tunning.domain.model.queries.GetReportByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReportQueryService {

    Optional<Report> handle(GetReportByIdQuery query);

    List<Report> handle(GetAllReportsQuery query);
}
