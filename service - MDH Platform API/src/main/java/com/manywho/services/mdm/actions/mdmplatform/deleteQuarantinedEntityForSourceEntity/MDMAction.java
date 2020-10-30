package com.manywho.services.mdm.actions.mdmplatform.deleteQuarantinedEntityForSourceEntity;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name="Delete Quarantine Entry for Source Entity", summary = "The Delete Quarantine Entry for Source Entity operation deletes the quarantine entry for a specified source entity from a specified universe (domain) hosted in a specified repository.", uri="/mdm/deleteQuarantinedEntityForSourceEntity")
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
	
	public static class Outputs {
		@Action.Output(name="Status", contentType=ContentType.String)
		private String status;
		
		public Outputs(String status)
		{
			this.status=status;
		}
	}
}
