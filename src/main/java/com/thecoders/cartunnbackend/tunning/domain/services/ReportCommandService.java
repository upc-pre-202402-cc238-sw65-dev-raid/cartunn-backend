package com.thecoders.cartunnbackend.tunning.domain.services;

import com.thecoders.cartunnbackend.tunning.domain.model.aggregates.Report;
import com.thecoders.cartunnbackend.tunning.domain.model.commands.CreateReportCommand;
import com.thecoders.cartunnbackend.tunning.domain.model.commands.DeleteReportCommand;
import com.thecoders.cartunnbackend.tunning.domain.model.commands.UpdateReportCommand;

import java.util.Optional;

public interface ReportCommandService {
    Long handle(CreateReportCommand command);
    Optional<Report> handle(UpdateReportCommand command);
    void handle(DeleteReportCommand command);
}
