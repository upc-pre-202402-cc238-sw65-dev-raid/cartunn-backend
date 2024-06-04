package com.thecoders.cartunnbackend.tunning.interfaces.rest;

import com.thecoders.cartunnbackend.tunning.domain.model.commands.DeleteReportCommand;
import com.thecoders.cartunnbackend.tunning.domain.model.queries.GetAllReportsQuery;
import com.thecoders.cartunnbackend.tunning.domain.model.queries.GetReportByIdQuery;
import com.thecoders.cartunnbackend.tunning.domain.services.ReportCommandService;
import com.thecoders.cartunnbackend.tunning.domain.services.ReportQueryService;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.resources.CreateReportResource;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.resources.ReportResource;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.resources.UpdateReportResource;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.transform.UpdateReportCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/tunning/reports", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tunning", description = "Tunning-Report Management Endpoints")

public class ReportsController {
    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    public ReportsController(ReportCommandService reportCommandService, ReportQueryService reportQueryService) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }
    @PostMapping
    public ResponseEntity<ReportResource> createReport(@RequestBody CreateReportResource createReportResource) {
        var createReportCommand = CreateReportCommandFromResourceAssembler.toCommandFromResource(createReportResource);
        var reportId = reportCommandService.handle(createReportCommand);
        if (reportId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getReportByIdQuery = new GetReportByIdQuery(reportId);
        var report = reportQueryService.handle(getReportByIdQuery);
        if (report.isEmpty()) return ResponseEntity.badRequest().build();
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
        return new ResponseEntity<>(reportResource, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ReportResource>> getAllReports() {
        var getAllReportsQuery = new GetAllReportsQuery();
        var reports = reportQueryService.handle(getAllReportsQuery);
        var reportResources = reports.stream().map(ReportResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(reportResources);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<ReportResource> updateReport(@PathVariable Long reportId, @RequestBody UpdateReportResource updateReportResource) {
        var updateReportCommand = UpdateReportCommandFromResourceAssembler.toCommandFromResource(reportId, updateReportResource);
        var updatedReport = reportCommandService.handle(updateReportCommand);
        if (updatedReport.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(updatedReport.get());
        return ResponseEntity.ok(reportResource);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable Long reportId) {
        var deleteReportCommand = new DeleteReportCommand(reportId);
        reportCommandService.handle(deleteReportCommand);
        return ResponseEntity.ok("Report with given id successfully deleted");
    }

}
