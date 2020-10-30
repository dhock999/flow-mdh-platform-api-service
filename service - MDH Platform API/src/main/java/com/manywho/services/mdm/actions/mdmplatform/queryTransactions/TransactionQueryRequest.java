package com.manywho.services.mdm.actions.mdmplatform.queryTransactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.dom4j.Document;
import org.w3c.dom.NodeList;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.Util;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;

@Type.Element(name = "TransactionQueryRequest")
public class TransactionQueryRequest implements Type{
//	<mdm:TransactionQueryRequest xmlns:mdm="http://mdm.api.platform.boomi.com/"
//			  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" offsetToken="abc" limit="10" includeEvents="25">
//	  <mdm:sourceEntityId></mdm:sourceEntityId>
//			   <mdm:sourceId>netsuite</mdm:sourceId>
//			   <mdm:startFromDate>2016-09-15T23:47:00</mdm:startFromDate>
//			   <mdm:startToDate>2016-09-15T23:48:59</mdm:startToDate>
//	   <mdm:sourceId></mdm:sourceId>
//	   <mdm:startFromDate></mdm:startFromDate>
//	   <mdm:startToDate></mdm:startToDate>
//	</mdm:TransactionQueryRequest>
	
	@Type.Identifier
	private String guid;
	
    @Type.Property(name = "Offset Token", contentType = ContentType.String)
    private String offsetToken;
    
	@Type.Property(name = "Record Limit", contentType = ContentType.Number)
    private long limit;
    
    @Type.Property(name = "Include Events", contentType = ContentType.Boolean)
    private Boolean includeEvents;
    
	@Type.Property(name = "Source Entity ID", contentType = ContentType.String)
    private String sourceEntityId;

	@Type.Property(name = "Source ID", contentType = ContentType.String)
    private String sourceId;

	@Type.Property(name = "Start From Date", contentType = ContentType.DateTime)
    private Date startFromDate;

	@Type.Property(name = "Start To Date", contentType = ContentType.DateTime)
    private Date startToDate;

	@Type.Property(name = "End From Date", contentType = ContentType.DateTime)
    private Date endFromDate;

	@Type.Property(name = "End To Date", contentType = ContentType.DateTime)
    private Date endToDate;
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getOffsetToken() {
		return offsetToken;
	}

	public void setOffsetToken(String offsetToken) {
		this.offsetToken = offsetToken;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public Boolean getIncludeEvents() {
		return includeEvents;
	}

	public void setIncludeEvents(Boolean includeEvents) {
		this.includeEvents = includeEvents;
	}

	public String getSourceEntityId() {
		return sourceEntityId;
	}

	public void setSourceEntityId(String sourceEntityId) {
		this.sourceEntityId = sourceEntityId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Date getStartFromDate() {
		return startFromDate;
	}

	public void setStartFromDate(Date startFromDate) {
		this.startFromDate = startFromDate;
	}

	public Date getStartToDate() {
		return startToDate;
	}

	public void setStartToDate(Date startToDate) {
		this.startToDate = startToDate;
	}

	public Date getEndFromDate() {
		return endFromDate;
	}

	public void setEndFromDate(Date endFromDate) {
		this.endFromDate = endFromDate;
	}

	public Date getEndToDate() {
		return endToDate;
	}

	public void setEndToDate(Date endToDate) {
		this.endToDate = endToDate;
	}

	public String getRequestXML()
	{
		StringBuilder sbPayload = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

		sbPayload.append(String.format("<mdm:TransactionQueryRequest xmlns:mdm=\"http://mdm.api.platform.boomi.com/\"\r\n" + 
				"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" offsetToken=\"%s\" limit=\"%d\" includeEvents=\"%s\">\r\n",
				offsetToken, limit, includeEvents));
		if (!Util.isNullOrEmpty(sourceEntityId))
			sbPayload.append(String.format("<mdm:sourceEntityId>%s</mdm:sourceEntityId>", sourceEntityId));
		if (!Util.isNullOrEmpty(sourceId))
			sbPayload.append(String.format("<mdm:sourceId>%s</mdm:sourceId>", sourceId));
		if (startFromDate!=null)
			sbPayload.append(String.format("<mdm:startFromDate>%s</mdm:startFromDate>", sdf.format(startFromDate)));
		if (startToDate!=null)
			sbPayload.append(String.format("<mdm:startToDate>%s</mdm:startToDate>", sdf.format(startToDate)));
		if (endFromDate!=null)
			sbPayload.append(String.format("<mdm:endFromDate>%s</mdm:endFromDate>", sdf.format(endFromDate)));
		if (endToDate!=null)
			sbPayload.append(String.format("<mdm:endToDate>%s</mdm:endToDate>", sdf.format(endToDate)));

		sbPayload.append("</mdm:TransactionQueryRequest>");
		return sbPayload.toString();
	}	
}
