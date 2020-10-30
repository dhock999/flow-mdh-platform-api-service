package com.manywho.services.mdm.actions.mdmplatform.resubmitStagedEntities;

import javax.inject.Inject;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionRequest;

public class StagingActionCommand implements ActionCommand<ServiceConfiguration, StagingAction, StagingAction.Inputs, StagingAction.Outputs>{

	AuthenticatedWho user;
    @Inject
    public StagingActionCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<StagingAction.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			StagingAction.Inputs input) {

		String resource = String.format("%s/universes/%s", input.getRepositoryID(), input.getUniverseID());
		String payload = StagingActionRequest.getRequestXML(input.getStagingActionFilter());
		int resultCount = StagingActionRequest.getResultCount(configuration,"repositories", "POST", resource, payload);
		return new ActionResponse<>(new StagingAction.Outputs(resultCount));
	}
}
