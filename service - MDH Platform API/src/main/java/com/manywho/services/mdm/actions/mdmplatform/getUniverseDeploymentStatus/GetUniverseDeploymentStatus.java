package com.manywho.services.mdm.actions.mdmplatform.getUniverseDeploymentStatus;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;
import com.manywho.services.mdm.actions.mdmplatform.deployUniverse.UniverseDeployment;

@Action.Metadata(name="Get Universe Deployment Status", summary = "This operation retrieves by deployment ID the status of the deployment of the model underlying a specified universe (domain) deployment in a specified account accessible to the authenticated user.", uri="/mdm/getUniverseDeploymentStatus")
public class GetUniverseDeploymentStatus {
	public static class Inputs{
	    @Action.Input(name = "Deployment ID", contentType = ContentType.String, required = true)
	    private String deploymentID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

	    public String getDeploymentID() {
			return deploymentID;
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
