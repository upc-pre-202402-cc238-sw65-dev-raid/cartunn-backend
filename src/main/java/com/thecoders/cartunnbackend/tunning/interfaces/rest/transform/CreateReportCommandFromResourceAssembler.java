package com.thecoders.cartunnbackend.tunning.interfaces.rest.transform;

import com.thecoders.cartunnbackend.tunning.domain.model.commands.CreateReportCommand;
import com.thecoders.cartunnbackend.tunning.interfaces.rest.resources.CreateReportResource;

public class CreateReportCommandFromResourceAssembler {
    public static CreateReportCommand toCommandFromResource(CreateReportResource resource) {
        return new CreateReportCommand(resource.modifiedPart(),resource.date(),resource.status());
    }
}
