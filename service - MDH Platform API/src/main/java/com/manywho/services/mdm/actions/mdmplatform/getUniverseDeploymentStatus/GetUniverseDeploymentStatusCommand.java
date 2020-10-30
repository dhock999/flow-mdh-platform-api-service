package com.manywho.services.mdm.actions.mdmplatform.getUniverseDeploymentStatus;

import javax.inject.Inject;

import org.dom4j.Document;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.deployUniverse.UniverseDeployment;

public class GetUniverseDeploymentStatusCommand implements ActionCommand<ServiceConfiguration, GetUniverseDeploymentStatus, GetUniverseDeploymentStatus.Inputs, GetUniverseDeploymentStatus.Outputs>{

	AuthenticatedWho user;
    @Inject
    public GetUniverseDeploymentStatusCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<GetUniverseDeploymentStatus.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			GetUniverseDeploymentStatus.Inputs input) {

		String resource = String.format("%s/deployments/%s", input.getUniverseID(), input.getDeploymentID());
		Document document = AtomsphereAPI.executeAPIXML(configuration, "universe", "GET", resource, null);
		UniverseDeployment universeDeployment = new UniverseDeployment(document.selectSingleNode("mdm:UniverseDeployment"));

		return new ActionResponse<>(new GetUniverseDeploymentStatus.Outputs(universeDeployment));
	}
}
