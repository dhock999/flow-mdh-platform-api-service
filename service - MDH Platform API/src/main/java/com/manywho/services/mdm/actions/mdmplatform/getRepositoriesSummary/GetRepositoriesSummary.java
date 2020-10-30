package com.manywho.services.mdm.actions.mdmplatform.getRepositoriesSummary;

import java.util.List;
import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.RepositorySummary;

@Action.Metadata(name="Get Repositories Summary", summary = "The Get Repositories Summary operation retrieves summary data for the repositories and universes (domains) under a specified account accessible to the authenticating user.", uri="/mdm/repositorySummaries")
public class GetRepositoriesSummary {
	public static class Inputs{
	}
	
	public static class Outputs {
		@Action.Output(name="Repositories Summary", contentType=ContentType.List)
		private List<RepositorySummary> repositories;
		
		public Outputs(List<RepositorySummary> repositories)
		{
			this.repositories = repositories;
		}
	}
}