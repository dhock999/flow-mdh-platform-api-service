package com.manywho.services.mdm.actions.mdmplatform.undeployUniverse;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name="Undeploy Universe", summary = "The Undeploy Universe operation undeploys the model underlying a specified universe hosted in a specified repository in a specified account accessible to the user. Undeploying the model removes the domain and with it the data and attached sources.", uri="/mdm/undeployUniverse")
public class UndeployUniverse {
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
		@Action.Output(name="Status Code", contentType=ContentType.String)
		private String status;
		public Outputs(String status)
		{
			this.status=status;
		}
	}
}
