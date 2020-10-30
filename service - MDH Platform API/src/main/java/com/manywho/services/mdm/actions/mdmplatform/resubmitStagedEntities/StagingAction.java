package com.manywho.services.mdm.actions.mdmplatform.resubmitStagedEntities;

import java.util.Date;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;

@Action.Metadata(name="Resubmit Staged Entities", summary = "The Resubmit Staged Entities operation sends a request to resubmit specified entities to a specified staging area for a source of a specified universe (domain) hosted in a specified repository.", uri="/mdm/resubmitStagedEntities")
public class StagingAction {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

//		@Action.Input(name = "Offset Token", contentType = ContentType.String)
//	    private String offsetToken;
//
//		@Action.Input(name = "Limits", contentType = ContentType.Number)
//	    private String limits;

		@Action.Input(name="Staging Action Filter", contentType = ContentType.Object)
		private StagingActionFilter stagingActionFilter;
		
		public String getRepositoryID() {
			return repositoryID;
		}

		public String getUniverseID() {
			return universeID;
		}

		public StagingActionFilter getStagingActionFilter() {
			return stagingActionFilter;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Result Count", contentType=ContentType.Number)
		private int resultCount;
		
		public Outputs(int resultCount)
		{
			this.resultCount=resultCount;
		}
	}
}
