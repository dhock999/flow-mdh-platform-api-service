package com.manywho.services.mdm.actions.mdmplatform.getUniverseSummary;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;

@Action.Metadata(name="Get Universe Summary", summary = "This operation retrieves summary data for a specified universe (domain) hosted in a specified repository under a specified account accessible to the authenticating user.", uri="/mdm/universeSummary")
public class GetUniverseSummary {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

	    public String getRepositoryID() {
			return repositoryID;
		}

		public String getUniverseID() {
			return universeID;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Universe Summary", contentType=ContentType.Object)
		private UniverseSummary universeSummary;
		public Outputs(UniverseSummary universeSummary)
		{
			this.universeSummary=universeSummary;
		}

		public UniverseSummary getUniverseSummary() {
			return universeSummary;
		}
	}
}
