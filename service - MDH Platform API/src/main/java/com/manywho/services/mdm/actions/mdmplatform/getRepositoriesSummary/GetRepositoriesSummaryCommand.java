package com.manywho.services.mdm.actions.mdmplatform.getRepositoriesSummary;

import java.util.List;

import javax.inject.Inject;
import org.dom4j.Document;
import org.dom4j.Node;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.RepositorySummary;

public class GetRepositoriesSummaryCommand implements ActionCommand<ServiceConfiguration, GetRepositoriesSummary, GetRepositoriesSummary.Inputs, GetRepositoriesSummary.Outputs>{

	AuthenticatedWho user;
    @Inject
    public GetRepositoriesSummaryCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<GetRepositoriesSummary.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			GetRepositoriesSummary.Inputs input) {

		Document document = AtomsphereAPI.executeAPIXML(configuration, "repositories", "GET", null, null);
		Node root = document.selectSingleNode("mdm:Repositories");
		List<RepositorySummary> repositories = RepositorySummary.getList(root.selectNodes("mdm:Repository"));
		return new ActionResponse<>(new GetRepositoriesSummary.Outputs(repositories));
	}
}
