package com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.dom4j.Document;
import org.w3c.dom.NodeList;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;

public class StagingQueryRequest {
//	<mdm:StagingQueryRequest xmlns:mdm="http://mdm.api.platform.boomi.com/"
//			  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" includeSummary="" includeRecords="" offsetToken="" limit="">
//			   <mdm:sourceId></mdm:sourceId>
//			   <mdm:stagingAreaId></mdm:stagingAreaId>
//			   <mdm:filter>
//			      <mdm:sourceEntityId></mdm:sourceEntityId>
//			      <mdm:entityResult></mdm:entityResult>
//			      <mdm:createDateRelative></mdm:createDateRelative>
//			      <mdm:createDateFrom></mdm:createDateFrom>
//			      <mdm:createDateTo></mdm:createDateTo>
//			   </mdm:filter>
//			</mdm:StagingQueryRequest>
	
	public static String getRequestXML(Boolean includeSummary, Boolean includeRecords, String offsetTokens, long limits, StagingActionFilter filter)
	{
		StringBuilder sbPayload = new StringBuilder();

		sbPayload.append(String.format("<mdm:StagingQueryRequest xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" includeSummary=\"%s\" includeRecords=\"%s\" offsetToken=\"%s\" limit=\"%d\">",
				includeSummary, includeRecords, offsetTokens, limits));
		sbPayload.append(filter.toXMLFragment());
		
		sbPayload.append("</mdm:StagingQueryRequest>");
		return sbPayload.toString();
	}	
}
