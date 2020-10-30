package com.manywho.services.mdm.actions.mdmplatform.deleteQuarantinedEntityForSourceEntity;

import javax.inject.Inject;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;

public class MDMActionCommand implements ActionCommand<ServiceConfiguration, MDMAction, MDMAction.Inputs, MDMAction.Outputs>{

	AuthenticatedWho user;
    @Inject
    public MDMActionCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<MDMAction.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			MDMAction.Inputs input) {

		String resource = String.format("%s/universes/%s/quarantine/sources/%s/entities/%s/delete", input.getRepositoryID(), input.getUniverseID(), input.getSourceId(), input.getEntityId());
		AtomsphereAPI.executeAPIXML(configuration, "repositories", "POST", resource, null);

		return new ActionResponse<>(new MDMAction.Outputs("OK"));
	}
}
