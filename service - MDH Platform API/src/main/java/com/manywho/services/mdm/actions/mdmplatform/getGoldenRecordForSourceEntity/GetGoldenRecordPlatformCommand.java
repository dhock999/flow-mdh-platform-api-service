package com.manywho.services.mdm.actions.mdmplatform.getGoldenRecordForSourceEntity;

import javax.inject.Inject;

import org.dom4j.Document;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;

public class GetGoldenRecordPlatformCommand implements ActionCommand<ServiceConfiguration, GetGoldenRecordPlatform, GetGoldenRecordPlatform.Inputs, GetGoldenRecordPlatform.Outputs>{

	AuthenticatedWho user;
    @Inject
    public GetGoldenRecordPlatformCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<GetGoldenRecordPlatform.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			GetGoldenRecordPlatform.Inputs input) {

		String resource = String.format("%s/universes/%s/records/sources/%s/entities/%s", input.getRepositoryID(), input.getUniverseID(), input.getEntityID());
		Document document = AtomsphereAPI.executeAPIXML(configuration, "repositories", "GET", resource, null);
		GoldenRecord goldenRecord = new GoldenRecord(document);

		return new ActionResponse<>(new GetGoldenRecordPlatform.Outputs(goldenRecord));
	}
}
