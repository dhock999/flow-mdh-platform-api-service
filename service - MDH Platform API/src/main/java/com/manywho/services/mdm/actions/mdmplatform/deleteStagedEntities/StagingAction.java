package com.manywho.services.mdm.actions.mdmplatform.deleteStagedEntities;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;

@Action.Metadata(name="Delete Staged Entities", summary = "This operation sends a request to delete specified entities in a specified staging area for a source of a specified universe (domain) hosted in a specified repository under a specified account accessible to the authenticating user.", uri="/mdm/deleteStagedEntities")
public class StagingAction {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

		@Action.Input(name="Staging Action Filter", contentType = ContentType.Object, required = true)
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
