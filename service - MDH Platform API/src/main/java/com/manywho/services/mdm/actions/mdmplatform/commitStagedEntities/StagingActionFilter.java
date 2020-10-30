package com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;
import com.manywho.services.mdm.actions.mdmplatform.Util;

@Type.Element(name = "StagingActionFilter")
public class StagingActionFilter implements Type{
	@Type.Identifier
	private String guid;
	
    @Type.Property(name = "Source ID", contentType = ContentType.String)
    private String sourceId;
    
	@Type.Property(name = "Staging Area ID", contentType = ContentType.String)
    private String stagingAreaId;
    
    @Type.Property(name = "Staged Entry ID (optional)", contentType = ContentType.String)
    private String stagedEntryId;
    
    @Type.Property(name = "Source Entity ID (optional)", contentType = ContentType.String)
    private String sourceEntityId;
    
	@Type.Property(name = "Entity Result(optional - COMPLETED.* | COMPLETED.CREATED etc.)", contentType = ContentType.String)
    private String entityResult;

	@Type.Property(name = "Create Date Relative (optional - PAST_HOUR | PAST_24_HOURS | PAST_WEEK).", contentType = ContentType.String)
    private String createDateRelative;

	@Type.Property(name = "Create Date From (optional - ignored if Create Date Relative specified)", contentType = ContentType.DateTime)
    private Date createDateFrom;

	@Type.Property(name = "Create Date To (optional - ignored if Create Date Relative specified)", contentType = ContentType.DateTime)
    private Date createDateTo;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStagingAreaId() {
		return stagingAreaId;
	}

	public void setStagingAreaId(String stagingAreaId) {
		this.stagingAreaId = stagingAreaId;
	}

	public String getStagedEntryId() {
		return stagedEntryId;
	}

	public void setStagedEntryId(String stagedEntryId) {
		this.stagedEntryId = stagedEntryId;
	}

	public String getSourceEntityId() {
		return sourceEntityId;
	}

	public void setSourceEntityId(String sourceEntityId) {
		this.sourceEntityId = sourceEntityId;
	}

	public String getEntityResult() {
		return entityResult;
	}

	public void setEntityResult(String entityResult) {
		this.entityResult = entityResult;
	}

	public String getCreateDateRelative() {
		return createDateRelative;
	}

	public void setCreateDateRelative(String createDateRelative) {
		this.createDateRelative = createDateRelative;
	}

	public Date getCreateDateFrom() {
		return createDateFrom;
	}

	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}

	public Date getCreateDateTo() {
		return createDateTo;
	}

	public void setCreateDateTo(Date createDateTo) {
		this.createDateTo = createDateTo;
	}
	
	//An xml snippet to embed in StagingAction and StagingQuery Requests
	public String toXMLFragment()
	{
		StringBuilder sbPayload = new StringBuilder();
		if (Util.isNullOrEmpty(sourceId))
			throw new RuntimeException("Source ID is required");
		sbPayload.append(String.format("<mdm:sourceId>%s</mdm:sourceId>", sourceId));
		if (Util.isNullOrEmpty(stagingAreaId))
			throw new RuntimeException("Staging Area ID is required");
		sbPayload.append(String.format("<mdm:stagingAreaId>%s</mdm:stagingAreaId>", stagingAreaId));
		
		sbPayload.append("<mdm:filter>");
		
		if (!Util.isNullOrEmpty(stagedEntryId))
			sbPayload.append(String.format("<mdm:stagedEntryId>%s</mdm:stagedEntryId>", stagedEntryId));

		if (!Util.isNullOrEmpty(sourceEntityId))
			sbPayload.append(String.format("<mdm:sourceEntityId>%s</mdm:sourceEntityId>", sourceEntityId));

		if (!Util.isNullOrEmpty(entityResult))
			sbPayload.append(String.format("<mdm:entityResult>%s</mdm:entityResult>", entityResult));

		if (!Util.isNullOrEmpty(createDateRelative))
			sbPayload.append(String.format("<mdm:createDateRelative>%s</mdm:createDateRelative>", createDateRelative));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		if (createDateFrom != null)
			sbPayload.append(String.format("<mdm:createDateFrom>%s</mdm:createDateFrom>", sdf.format(createDateFrom)));

		if (createDateTo != null)
			sbPayload.append(String.format("<mdm:createDateTo>%s</mdm:createDateTo>", sdf.format(createDateTo)));

		sbPayload.append("</mdm:filter>");
		return sbPayload.toString();
	}
}
