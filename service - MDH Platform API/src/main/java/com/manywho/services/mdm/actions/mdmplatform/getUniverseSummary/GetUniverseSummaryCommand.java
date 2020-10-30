package com.manywho.services.mdm.actions.mdmplatform.getUniverseSummary;

import javax.inject.Inject;

import org.dom4j.Document;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;

public class GetUniverseSummaryCommand implements ActionCommand<ServiceConfiguration, GetUniverseSummary, GetUniverseSummary.Inputs, GetUniverseSummary.Outputs>{

	AuthenticatedWho user;
    @Inject
    public GetUniverseSummaryCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<GetUniverseSummary.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			GetUniverseSummary.Inputs input) {

		String resource = String.format("%s/universe/%s", input.getRepositoryID(), input.getUniverseID());
		Document document = AtomsphereAPI.executeAPIXML(configuration, "repositories", "GET", resource, null);
		UniverseSummary universe = new UniverseSummary(document.selectSingleNode("mdm:Universe"));

		return new ActionResponse<>(new GetUniverseSummary.Outputs(universe));
	}
}
