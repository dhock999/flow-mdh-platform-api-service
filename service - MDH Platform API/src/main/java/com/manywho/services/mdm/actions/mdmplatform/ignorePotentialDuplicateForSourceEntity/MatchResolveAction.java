package com.manywho.services.mdm.actions.mdmplatform.ignorePotentialDuplicateForSourceEntity;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name="Ignore Potential Duplicate for Source Entity", summary = "This operation resolves a quarantine entry for a potential duplicate matching error, where the quarantine entry is associated with a specified source entity from a specified universe (domain) hosted in a specified repository.", uri="/mdm/ignorePotentialDuplicateForSourceEntity")
public class MatchResolveAction {
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
		@Action.Output(name="Match Resolve Response", contentType=ContentType.Object)
		private MatchResolveResponse matchResolveResponse;
		
		public Outputs(MatchResolveResponse matchResolveResponse)
		{
			this.matchResolveResponse=matchResolveResponse;
		}
	}
}
