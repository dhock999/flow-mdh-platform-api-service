package com.manywho.services.mdm.actions.mdmplatform.deleteQuarantineEntryForTransaction;

import javax.inject.Inject;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;

public class TransactionActionCommand implements ActionCommand<ServiceConfiguration, TransactionAction, TransactionAction.Inputs, TransactionAction.Outputs>{

	AuthenticatedWho user;
    @Inject
    public TransactionActionCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<TransactionAction.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			TransactionAction.Inputs input) {

		String resource = String.format("%s/universes/%s/quarantine/%s/delete", input.getRepositoryID(), input.getUniverseID(), input.getTransactionId());
		AtomsphereAPI.executeAPIXML(configuration, "repositories", "POST", resource, null);

		return new ActionResponse<>(new TransactionAction.Outputs("OK"));
	}
}
