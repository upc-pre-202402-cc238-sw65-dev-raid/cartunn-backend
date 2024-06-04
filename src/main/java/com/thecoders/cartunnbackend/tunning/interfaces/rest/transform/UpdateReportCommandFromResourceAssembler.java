package com.thecoders.cartunnbackend.tunning.interfaces.rest.transform;

import com.thecoders.cartunnbackend.tunning.domain.model.commands.UpdateReportCommand;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.resources.UpdateReportResource;

public class UpdateReportCommandFromResourceAssembler {
    public static UpdateReportCommand toCommandFromResource(Long reportId, UpdateReportResource resource){
        return new UpdateReportCommand(reportId, resource.modifiedPart(), resource.date(), resource.status());
    }
}
