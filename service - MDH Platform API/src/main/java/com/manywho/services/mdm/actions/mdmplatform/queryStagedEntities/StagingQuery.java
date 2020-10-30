package com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities;

import java.util.List;
import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;

@Action.Metadata(name="Query Staged Entities", summary = "The Query Staged Entities operation sends a query of the entities in a specified staging area for a specified universe (domain) hosted in a specified repository under a specified account accessible to the authenticating user.", uri="/mdm/queryStagedEntities")
public class StagingQuery {
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
		@Action.Output(name="Staging Entities", contentType=ContentType.List)
		private List<StagingEntry> stagingEntries;
		
		@Action.Output(name="Staging Area Summary", contentType=ContentType.List)
		private List<EntityResultSummary> stagingAreaSummary;
		
		@Action.Output(name="Title Field Name", contentType=ContentType.String)
		private String titleFieldName;

		@Action.Output(name="Total Count", contentType=ContentType.Number)
		private long totalCount;

		@Action.Output(name="Result Count", contentType=ContentType.Number)
		private long resultCount;

		public Outputs(List<StagingEntry> stagingEntries, List<EntityResultSummary> stagingAreaSummary, String titleFieldName, long totalCount, long resultCount)
		{
			this.stagingEntries=stagingEntries;
			this.stagingAreaSummary=stagingAreaSummary;
			this.titleFieldName=titleFieldName;
			this.totalCount=totalCount;
			this.resultCount=resultCount;
		}
	}
}
