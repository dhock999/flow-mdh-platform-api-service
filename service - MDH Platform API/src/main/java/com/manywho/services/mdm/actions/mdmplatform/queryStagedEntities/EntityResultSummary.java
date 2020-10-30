package com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.dom4j.Node;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

//<mdm:entityResultSummary count="3" name="QUARANTINED.POSSIBLE_DUPLICATE"/>
@Type.Element(name = "EntityResultSummary")
public class EntityResultSummary implements Type{
	@Type.Identifier
	private String guid;
	@Type.Property(name = "Count", contentType = ContentType.Number)
	private int count;
	@Type.Property(name = "Name", contentType = ContentType.String)
	private String name;	
	
	public EntityResultSummary()
	{		
	}
	
	public EntityResultSummary(Node document)
	{
		this.guid=UUID.randomUUID().toString();
		this.count=Integer.parseInt(document.selectSingleNode("@count").getText());
		this.name=document.selectSingleNode("@name").getText();
	}

	public String getGuid() {
		return guid;
	}
	public int getCount() {
		return count;
	}
	public String getName() {
		return name;
	}
	
	public static List<EntityResultSummary> getList(Node document)
	{
	    List<Node> list = document.selectNodes("mdm:StagingAreaSummary/mdm:entityResultSummary");
	    List<EntityResultSummary> items = new ArrayList<>();
	    for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
	    	EntityResultSummary item = new EntityResultSummary(iter.next());
	        items.add(item);
	    }	
	    return items;
	}
}
