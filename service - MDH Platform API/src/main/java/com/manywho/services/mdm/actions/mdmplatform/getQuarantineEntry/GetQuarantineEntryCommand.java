package com.manywho.services.mdm.actions.mdmplatform.getQuarantineEntry;

import javax.inject.Inject;

import org.dom4j.Document;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;

public class GetQuarantineEntryCommand implements ActionCommand<ServiceConfiguration, GetQuarantineEntry, GetQuarantineEntry.Inputs, GetQuarantineEntry.Outputs>{

	AuthenticatedWho user;
    @Inject
    public GetQuarantineEntryCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<GetQuarantineEntry.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			GetQuarantineEntry.Inputs input) {

		String resource = String.format("%s/universes/%s/quarantine/%s", input.getRepositoryID(), input.getUniverseID(), input.getTransactionID());
		Document document = AtomsphereAPI.executeAPIXML(configuration, "repositories", "GET", resource, null);
		QuarantineEntry quarantineRecord = new QuarantineEntry(document);

		return new ActionResponse<>(new GetQuarantineEntry.Outputs(quarantineRecord));
	}
}
