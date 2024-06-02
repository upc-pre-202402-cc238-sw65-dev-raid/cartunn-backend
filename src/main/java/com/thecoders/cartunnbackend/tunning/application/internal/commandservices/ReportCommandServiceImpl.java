package com.thecoders.cartunnbackend.tunning.application.internal.commandservices;

import com.thecoders.cartunnbackend.tunning.domain.model.aggregates.Report;
import com.thecoders.cartunnbackend.tunning.domain.model.commands.CreateReportCommand;
import com.thecoders.cartunnbackend.tunning.domain.model.commands.DeleteReportCommand;
import com.thecoders.cartunnbackend.tunning.domain.model.commands.UpdateReportCommand;
import com.thecoders.cartunnbackend.tunning.domain.services.ReportCommandService;
import com.thecoders.cartunnbackend.tunning.infrastructure.persitence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Long handle(CreateReportCommand command) {
        if (reportRepository.existsByModifiedPart(command.modifiedPart())) {
            throw new IllegalArgumentException("Report with same title already exists");
        }
        var report = new Report(command);
        try {
            reportRepository.save(report);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving product: " + e.getMessage());
        }
        return report.getId();
    }
    @Override
    public Optional<Report> handle(UpdateReportCommand command) {
        if (reportRepository.existsByModifiedPartAndIdIsNot(command.modifiedPart(), command.id()))
            throw new IllegalArgumentException("Product with same title already exists");
        var result = reportRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Product does not exist");
        var reportToUpdate = result.get();
        try {
            var updatedReport = reportRepository.save(reportToUpdate.updateInformation(command.modifiedPart(), command.date(), command.status()));
            return Optional.of(updatedReport);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating product: " + e.getMessage());
        }
    }
    @Override
    public void handle(DeleteReportCommand command) {
        if (!reportRepository.existsById(command.reportId())) {
            throw new IllegalArgumentException("Product does not exist");
        }
        try {
            reportRepository.deleteById(command.reportId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting product: " + e.getMessage());
        }
    }

}
