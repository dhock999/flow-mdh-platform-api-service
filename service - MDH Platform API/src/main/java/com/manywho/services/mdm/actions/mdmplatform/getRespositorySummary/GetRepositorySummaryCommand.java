package com.manywho.services.mdm.actions.mdmplatform.getRespositorySummary;

import javax.inject.Inject;
import org.dom4j.Document;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.RepositorySummary;

public class GetRepositorySummaryCommand implements ActionCommand<ServiceConfiguration, GetRepositorySummary, GetRepositorySummary.Inputs, GetRepositorySummary.Outputs>{

	AuthenticatedWho user;
    @Inject
    public GetRepositorySummaryCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<GetRepositorySummary.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			GetRepositorySummary.Inputs input) {

		String resource = input.getRepositoryID();
		Document document = AtomsphereAPI.executeAPIXML(configuration, "repositories", "GET", resource, null);
		RepositorySummary repository = new RepositorySummary(document.selectSingleNode("mdm:Repository"));
		return new ActionResponse<>(new GetRepositorySummary.Outputs(repository));
	}
}
