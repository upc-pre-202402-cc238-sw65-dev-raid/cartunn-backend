package com.thecoders.cartunnbackend.tunning.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateReportResource(String modifiedPart, LocalDate date, String status) {
}
