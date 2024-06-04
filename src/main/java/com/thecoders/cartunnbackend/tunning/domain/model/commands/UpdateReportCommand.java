package com.thecoders.cartunnbackend.tunning.domain.model.commands;

import java.time.LocalDate;

public record UpdateReportCommand(Long id, String modifiedPart, LocalDate date, String status) {
}
