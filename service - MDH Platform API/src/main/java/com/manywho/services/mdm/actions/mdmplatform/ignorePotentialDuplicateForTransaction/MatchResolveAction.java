package com.manywho.services.mdm.actions.mdmplatform.ignorePotentialDuplicateForTransaction;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.ignorePotentialDuplicateForSourceEntity.MatchResolveResponse;

@Action.Metadata(name="Ignore Potential Duplicate for Transaction", summary = "The Ignore Potential Duplicate for Transaction operation resolves a quarantine entry for a potential duplicate matching error, where the quarantine entry is associated with a specified transaction from a specified universe hosted in the repository.", uri="/mdm/ignorePotentialDuplicateForTransaction")
public class MatchResolveAction {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

	    @Action.Input(name = "Transaction ID", contentType = ContentType.String, required = true)
	    private String transactionID;
	    
		public String getRepositoryID() {
			return repositoryID;
		}

		public String getUniverseID() {
			return universeID;
		}

	    public String getTransactionID() {
			return transactionID;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Match Resolve Response", contentType=ContentType.Object)
		private MatchResolveResponse matchResolveResponse;
		
		public Outputs(MatchResolveResponse matchResolveResponse)
		{
			this.matchResolveResponse=matchResolveResponse;
		}
	}
}
