package com.manywho.services.mdm.actions.mdmplatform.ignorePotentialDuplicateForSourceEntity;

import javax.inject.Inject;

import org.dom4j.Document;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;

public class MatchResolveActionCommand implements ActionCommand<ServiceConfiguration, MatchResolveAction, MatchResolveAction.Inputs, MatchResolveAction.Outputs>{

	AuthenticatedWho user;
    @Inject
    public MatchResolveActionCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<MatchResolveAction.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			MatchResolveAction.Inputs input) {

		String resource = String.format("%s/universes/%s/quarantine/sources/%s/entities/%s/createDuplicate", input.getRepositoryID(), input.getUniverseID(), input.getSourceId(), input.getEntityId());
		Document document = AtomsphereAPI.executeAPIXML(configuration, "repositories", "POST", resource, null);

		return new ActionResponse<>(new MatchResolveAction.Outputs(new MatchResolveResponse(document)));
	}
}
