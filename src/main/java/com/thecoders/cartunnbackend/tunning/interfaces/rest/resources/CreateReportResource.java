package com.thecoders.cartunnbackend.tunning.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateReportResource(String modifiedPart, LocalDate date, String status) {
}
