package com.thecoders.cartunnbackend.tunning.domain.exceptions;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(Long aLong) {
        super("Report with id " + aLong + " not found");
    }
}
