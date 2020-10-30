package com.manywho.services.mdm.actions.mdmplatform.getRespositorySummary;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.RepositorySummary;

@Action.Metadata(name="Get Repository Summary", summary = "This operation retrieves summary data for a specified repository and its hosted universes (domains) under a specified account accessible to the authenticating user.", uri="/mdm/repositorySummary")
public class GetRepositorySummary {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;
	    
	    public String getRepositoryID() {
			return repositoryID;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Repository Summary", contentType=ContentType.Object)
		private RepositorySummary repository;
		
		public Outputs(RepositorySummary repository)
		{
			this.repository = repository;
		}
	}
}