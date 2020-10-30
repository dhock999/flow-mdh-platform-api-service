package com.manywho.services.mdm.actions.mdmplatform.queryTransactions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.dom4j.Node;

import com.manywho.services.mdm.actions.mdmplatform.Util;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

//<mdm:Transaction stateDetail="CREATED" state="COMPLETED" endDate="2016-09-15T23:47:45Z" 
//startDate="2016-09-15T23:47:44Z" sourceEntityId="810BD45E0281301D867DE638C1BC10" sourceId="netsuite" 
//id="c49ba11d-49d9-4c27-8daf-cc9c7df4650a">
// <mdm:event eventType="SUBMIT" eventDate="2016-09-15T23:47:44Z">
//   <mdm:description>Entity '810BD45E0281301D867DE638C1BC10' contributed by source 'Netsuite'.</mdm:description>
// </mdm:event>
// <mdm:event eventType="COMPLETE" eventDate="2016-09-15T23:47:45Z">
//   <mdm:description>Golden record 'c9d2ab00-edb3-4f93-b279-b9e45ad70cd5' created.</mdm:description>
// </mdm:event>
//</mdm:Transaction>
@Type.Element(name = "Transaction")
public class Transaction implements Type{
	@Type.Identifier
	private String guid;

	@Type.Property(name = "State Detail", contentType = ContentType.String)
	private String stateDetail;	
	
	@Type.Property(name = "State", contentType = ContentType.String)
	private String state;	
	
	@Type.Property(name = "Start Date", contentType = ContentType.DateTime)
	private Date startDate;	
		
	@Type.Property(name = "End Date", contentType = ContentType.DateTime)
	private Date endDate;	
		
	@Type.Property(name = "Source Entity ID", contentType = ContentType.String)
	private String sourceEntityId;	
	
	@Type.Property(name = "Source ID", contentType = ContentType.String)
	private String sourceId;	
	
	@Type.Property(name = "ID", contentType = ContentType.String)
	private String id;	
	
	@Type.Property(name = "Events", contentType = ContentType.List)
	private List<Event> events;	
	
	public Transaction()
	{		
	}
	
	public Transaction(Node document)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.guid=UUID.randomUUID().toString();
		this.stateDetail = Util.getSingleNode(document,"@stateDetail");
		this.state = Util.getSingleNode(document,"@state");
		try {
			Node node = document.selectSingleNode("@startDate");
			if (node!=null)
				this.startDate = sdf.parse(node.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		try {
			Node node = document.selectSingleNode("@endDate");
			if (node!=null)
				this.endDate = sdf.parse(node.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.sourceEntityId = Util.getSingleNode(document, "@sourceEntityId");
		this.sourceId = Util.getSingleNode(document, "@sourceId");
		this.id = Util.getSingleNode(document,"@id");
		this.events = Event.getList(document);
	}

	public String getGuid() {
		return guid;
	}

	public String getStateDetail() {
		return stateDetail;
	}

	public String getState() {
		return state;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getSourceEntityId() {
		return sourceEntityId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public String getId() {
		return id;
	}

	public List<Event> getEvents() {
		return events;
	}
	
	public static List<Transaction> getList(Node document)
	{
	    List<Node> list = document.selectNodes("mdm:Transaction");
	    List<Transaction> items = new ArrayList<>();
	    for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
	    	Transaction item = new Transaction(iter.next());
	        items.add(item);
	    }	
	    return items;
	}
}
