package com.manywho.services.mdm.actions.mdmplatform.undeployUniverse;

import javax.inject.Inject;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;

public class UndeployUniverseCommand implements ActionCommand<ServiceConfiguration, UndeployUniverse, UndeployUniverse.Inputs, UndeployUniverse.Outputs>{

	AuthenticatedWho user;
    @Inject
    public UndeployUniverseCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<UndeployUniverse.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			UndeployUniverse.Inputs input) {

		String resource = String.format("%s/universe/%s", input.getRepositoryID(), input.getUniverseID());
		AtomsphereAPI.executeAPIXML(configuration, "repositories", "POST", resource, null);

		return new ActionResponse<>(new UndeployUniverse.Outputs("OK"));
	}
}
