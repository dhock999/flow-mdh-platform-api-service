package com.manywho.services.mdm.actions.mdmplatform.deleteQuarantineEntryForTransaction;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name="Delete Quarantine Entry for Transaction", summary = "This operation deletes the quarantine entry associated with a specified transaction from a specified universe (domain) hosted in a specified repository.", uri="/mdm/deleteQuarantineEntryForTransaction")
public class TransactionAction {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

	    @Action.Input(name = "Transaction ID", contentType = ContentType.String, required = true)
	    private String transactionId;
	    
		public String getRepositoryID() {
			return repositoryID;
		}

		public String getUniverseID() {
			return universeID;
		}

	    public String getTransactionId() {
			return transactionId;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Status", contentType=ContentType.String)
		private String status;
		
		public Outputs(String status)
		{
			this.status=status;
		}
	}
}
