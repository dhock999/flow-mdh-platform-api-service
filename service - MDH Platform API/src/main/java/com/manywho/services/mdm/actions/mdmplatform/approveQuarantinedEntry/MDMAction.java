package com.manywho.services.mdm.actions.mdmplatform.approveQuarantinedEntry;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name="Approve Quarantined Entity", summary = "The Approve Quarantined Entity operation approves a quarantined entity, given its source ID and source entity ID, from a specified universe (domain) hosted in a specified repository under a specified account accessible to the user.", uri="/mdm/approveQuarantinedEntry")
public class MDMAction {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

	    @Action.Input(name = "Source ID", contentType = ContentType.String, required = true)
	    private String sourceId;
	    
		@Action.Input(name = "Entity ID", contentType = ContentType.String, required = true)
	    private String entityId;
	    
		public String getRepositoryID() {
			return repositoryID;
		}

		public String getUniverseID() {
			return universeID;
		}

	    public String getSourceId() {
			return sourceId;
		}

		public String getEntityId() {
			return entityId;
		}
	}
	
//	<mdm:ApproveResponse xmlns:mdm="http://mdm.api.platform.boomi.com/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
//	  <mdm:success>true</mdm:success>
//	  <mdm:transaction stateDetail="CREATED" state="COMPLETED" updatedDate="2016-12-13T14:51:39.201Z" id="39cf510e-460b-4857-b615-70b63844e84e"/>
//	</mdm:ApproveResponse>
	public static class Outputs {
		@Action.Output(name="Approve Response", contentType=ContentType.Object)
		private ApproveResponse response;
		
		public Outputs(ApproveResponse response)
		{
			this.response = response;
		}
	}
}