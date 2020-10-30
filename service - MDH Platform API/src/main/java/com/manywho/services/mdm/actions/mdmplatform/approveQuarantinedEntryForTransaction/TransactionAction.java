package com.manywho.services.mdm.actions.mdmplatform.approveQuarantinedEntryForTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.dom4j.Document;
import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.approveQuarantinedEntry.ApproveResponse;

@Action.Metadata(name="Approve Quarantined Entity for Transaction", summary = "The Approve Quarantined Entity for Transaction operation approves the quarantined entity associated with a specified transaction from a specified universe (domain) hosted in a specified repository under a specified account accessible to the user.", uri="/mdm/approveQuarantinedEntryForTransaction")
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
		@Action.Output(name="Approve Response", contentType=ContentType.Object)
		private ApproveResponse response;
		
		public Outputs(ApproveResponse response)
		{
			this.response = response;
		}
	}
}
