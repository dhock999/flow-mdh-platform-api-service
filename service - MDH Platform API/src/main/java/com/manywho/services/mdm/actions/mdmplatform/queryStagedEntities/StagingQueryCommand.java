package com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities;

import java.util.List;

import javax.inject.Inject;

import org.dom4j.Document;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;

public class StagingQueryCommand implements ActionCommand<ServiceConfiguration, StagingQuery, StagingQuery.Inputs, StagingQuery.Outputs>{

	AuthenticatedWho user;
    @Inject
    public StagingQueryCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<StagingQuery.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			StagingQuery.Inputs input) {

		String resource = String.format("%s/universes/%s/staging", input.getRepositoryID(), input.getUniverseID());
		String payload = StagingQueryRequest.getRequestXML(true, true, null, 10, input.getStagingActionFilter());
		
		Document stagingQueryResponse = AtomsphereAPI.executeAPIXML(configuration, "repositories", "POST", resource, payload);
		List<StagingEntry> stagingEntries = StagingEntry.getList(stagingQueryResponse);
		List<EntityResultSummary> stagingAreaSummary = EntityResultSummary.getList(stagingQueryResponse);
		String titleFieldName = stagingQueryResponse.selectSingleNode("mdm:StagingAreaSummary/@titleFieldName").getText();
		long totalCount = Long.parseLong(stagingQueryResponse.selectSingleNode("mdm:StagingAreaSummary/@totalCount").getText());
		long resultCount = Long.parseLong(stagingQueryResponse.selectSingleNode("mdm:StagingAreaSummary/@resultCount").getText());;
		return new ActionResponse<>(new StagingQuery.Outputs(stagingEntries, stagingAreaSummary, titleFieldName, totalCount, resultCount));
	}
}
