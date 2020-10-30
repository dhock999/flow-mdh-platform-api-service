package com.manywho.services.mdm.actions.mdmplatform.getGoldenRecordForSourceEntity;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name="Get Golden Record for Source Entity (Platform)", summary = "The Get Golden Record for Source Entity operation retrieves the entity corresponding to the golden record linked to a specified source entity in a specified universe (domain) hosted in a specified repository.", uri="/mdm/getGoldenRecordForSourceEntity")
public class GetGoldenRecordPlatform {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

		@Action.Input(name = "Entity ID", contentType = ContentType.String, required = true)
	    private String entityID;

	    public String getRepositoryID() {
			return repositoryID;
		}

		public String getUniverseID() {
			return universeID;
		}

		public String getEntityID() {
			return entityID;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Golden Record", contentType=ContentType.Object)
		private GoldenRecord goldenRecord;
		public Outputs(GoldenRecord goldenRecord)
		{
			this.goldenRecord=goldenRecord;
		}

		public GoldenRecord getUniverseSummary() {
			return goldenRecord;
		}
	}
}
