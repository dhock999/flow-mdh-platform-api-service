package com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities;

import org.dom4j.Document;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;

public class StagingActionRequest {
//	<mdm:StagingActionRequest xmlns:mdm="http://mdm.api.platform.boomi.com/"
//			  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
//			   <mdm:sourceId></mdm:sourceId>
//			   <mdm:stagingAreaId></mdm:stagingAreaId>
//			   <mdm:filter>
//			      <mdm:stagedEntryId></mdm:stagedEntryId>  
//			      <mdm:sourceEntityId></mdm:sourceEntityId>
//			      <mdm:entityResult></mdm:entityResult>
//			      <mdm:createDateRelative></mdm:createDateRelative>
//			      <mdm:createDateFrom></mdm:createDateFrom>
//			      <mdm:createDateTo></mdm:createDateTo>
//			   </mdm:filter>
//	</mdm:StagingActionRequest>
	public static String getRequestXML(StagingActionFilter filter)
	{
		StringBuilder sbPayload = new StringBuilder();

		sbPayload.append("<mdm:StagingActionRequest xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		sbPayload.append(filter.toXMLFragment());
		sbPayload.append("</mdm:StagingActionRequest>");
		return sbPayload.toString();
	}
	
//	<mdm:MdmActionResponse xmlns:mdm="http://mdm.api.platform.boomi.com/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
//	   <mdm:resultCount>2</mdm:resultCount>
//	</mdm:MdmActionResponse>
	public static int getResultCount (ServiceConfiguration configuration, String entityName, String method, String resource, String payload)
	{
		Document stagingActionResponse = AtomsphereAPI.executeAPIXML(configuration, entityName, method, resource, payload);
		int resultCount=Integer.parseInt(stagingActionResponse.selectSingleNode("//mdm:resultCount").getText());
		return resultCount;
	}
}
