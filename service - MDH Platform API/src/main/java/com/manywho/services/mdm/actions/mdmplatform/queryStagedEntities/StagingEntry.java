package com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.Node;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;
import com.manywho.services.mdm.actions.mdmplatform.Util;

// <mdm:StagingEntry result="QUARANTINED.POSSIBLE_DUPLICATE" sourceEntityId="34" createdDate="2015-07-21T19:35:40Z" titleFieldValue="Baker" stagedEntryId="14"/>
@Type.Element(name = "StagingEntry")
public class StagingEntry implements Type{
	@Type.Identifier
	private String guid;
	@Type.Property(name = "Result", contentType = ContentType.String)
	private String result;
	@Type.Property(name = "Source Entity ID", contentType = ContentType.Number)
	private int sourceEntityId;	
	@Type.Property(name = "Created Date", contentType = ContentType.DateTime)
	private String createdDate;	
	@Type.Property(name = "Title Field Value", contentType = ContentType.String)
	private String titleFieldValue;	
	@Type.Property(name = "Staged Entry ID", contentType = ContentType.Number)
	private int stagedEntryId;	
	
	public StagingEntry()
	{		
	}
	
	public StagingEntry(Node document)
	{
		this.guid=UUID.randomUUID().toString();
		this.result=Util.getSingleNode(document, "@result");
		this.sourceEntityId=Integer.parseInt(document.selectSingleNode("@sourceEntityId").getText());
		this.createdDate=document.selectSingleNode("@createdDate").getText();
		this.titleFieldValue=Util.getSingleNode(document, "@titleFieldValue");
		this.stagedEntryId=Integer.parseInt(document.selectSingleNode("@stagedEntryId").getText());
	}

	public String getGuid() {
		return guid;
	}

	public String getResult() {
		return result;
	}

	public int getSourceEntityId() {
		return sourceEntityId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getTitleFieldValue() {
		return titleFieldValue;
	}

	public int getStagedEntryId() {
		return stagedEntryId;
	}
	
	public static List<StagingEntry> getList(Node document)
	{
	    List<Node> list = document.selectNodes("mdm:StagingEntry");
	    List<StagingEntry> items = new ArrayList<>();
	    for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
	        StagingEntry item = new StagingEntry(iter.next());
	        items.add(item);
	    }	
	    return items;
	}
}
