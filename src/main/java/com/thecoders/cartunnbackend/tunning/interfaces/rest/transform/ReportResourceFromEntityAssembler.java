package com.thecoders.cartunnbackend.tunning.interfaces.rest.transform;

import com.thecoders.cartunnbackend.tunning.domain.model.aggregates.Report;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.resources.ReportResource;

public class ReportResourceFromEntityAssembler {
    public static ReportResource toResourceFromEntity(Report entity) {
        return new ReportResource(entity.getId(), entity.getModifiedPart(), entity.getDate(), entity.getStatus());
    }
}

