package com.manywho.services.mdm.actions.mdmplatform.queryTransactions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.dom4j.Node;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;
import com.manywho.services.mdm.actions.mdmplatform.Util;

// <mdm:event eventType="COMPLETE" eventDate="2016-09-15T23:47:45Z">
//   <mdm:description>Golden record 'c9d2ab00-edb3-4f93-b279-b9e45ad70cd5' created.</mdm:description>
// </mdm:event>

@Type.Element(name = "Event")
public class Event implements Type{
	@Type.Identifier
	private String guid;

	@Type.Property(name = "Event Type", contentType = ContentType.String)
	private String eventType;	
	
	@Type.Property(name = "Event Date", contentType = ContentType.DateTime)
	private Date eventDate;	
		
	@Type.Property(name = "Description", contentType = ContentType.String)
	private String description;	
	
	public Event()
	{		
	}
	
	public Event(Node document)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
		this.guid=UUID.randomUUID().toString();
		this.eventType = Util.getSingleNode(document, "@eventType");
		try {
			this.eventDate = sdf.parse(document.selectSingleNode("@eventDate").getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.description = Util.getSingleNode(document, "mdm:description");
	}

	public static List<Event> getList(Node document)
	{
	    List<Node> list = document.selectNodes("mdm:event");
	    List<Event> items = new ArrayList<>();
	    for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
	    	Event item = new Event(iter.next());
	        items.add(item);
	    }	
	    return items;
	}

	public String getGuid() {
		return guid;
	}

	public String getEventType() {
		return eventType;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public String getDescription() {
		return description;
	}
}
