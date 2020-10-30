package com.manywho.services.mdm.actions.mdmplatform.deployUniverse;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;

@Action.Metadata(name="Deploy Universe", summary = "The Deploy Universe operation requests deployment to a specified respository of a specified version of the model underlying a universe (domain) under a specified account accessible to the authenticated user.", uri="/mdm/deployUniverse")
public class DeployUniverse {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Version ID", contentType = ContentType.String, required = true)
	    private String versionID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

	    public String getRepositoryID() {
			return repositoryID;
		}

	    public String getVersionID() {
			return versionID;
		}

		public String getUniverseID() {
			return universeID;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Universe Deployment", contentType=ContentType.Object)
		private UniverseDeployment universeDeployment;
		public Outputs(UniverseDeployment universeDeployment)
		{
			this.universeDeployment=universeDeployment;
		}
	}
}
