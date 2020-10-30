package com.manywho.services.mdm.actions.mdmplatform.getQuarantineEntry;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;

@Action.Metadata(name="Get Quarantine Entry", summary = "The Get Quarantine Entry operation retrieves a specified quarantine entry in a specified universe (domain) hosted in a specified repository under a specified account accessible to the authenticating user.", uri="/mdm/getQuarantineEntry")
public class GetQuarantineEntry {
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
		@Action.Output(name="Quarantine Entry", contentType=ContentType.Object)
		private QuarantineEntry quarantineRecord;
		public Outputs(QuarantineEntry quarantineRecord)
		{
			this.quarantineRecord=quarantineRecord;
		}
	}
}
