package com.manywho.services.mdm.actions.mdmplatform.deployUniverse;

import javax.inject.Inject;

import org.dom4j.Document;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;

public class DeployUniverseCommand implements ActionCommand<ServiceConfiguration, DeployUniverse, DeployUniverse.Inputs, DeployUniverse.Outputs>{

	AuthenticatedWho user;
    @Inject
    public DeployUniverseCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<DeployUniverse.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			DeployUniverse.Inputs input) {

		String resource = String.format("%s/deploy?version=%s&repositoryId=%s", input.getUniverseID(), input.getVersionID(), input.getRepositoryID());
		Document document = AtomsphereAPI.executeAPIXML(configuration, "universe", "POST", resource, null);
		UniverseDeployment universeDeployment = new UniverseDeployment(document.selectSingleNode("mdm:UniverseDeployment"));

		return new ActionResponse<>(new DeployUniverse.Outputs(universeDeployment));
	}
}
