package com.manywho.services.mdm.actions.mdmplatform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.dom4j.Node;
import org.w3c.dom.NodeList;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

//<mdm:Universe name="Accounts" id="04ea04cc-9df8-4004-bc00-a21b7dede1bb">
//<mdm:goldenRecords>811</mdm:goldenRecords>
//<mdm:quarantinedRecords>21</mdm:quarantinedRecords>
//<mdm:pendingBatches>2</mdm:pendingBatches>
//<mdm:pendingBatchRecords>135</mdm:pendingBatchRecords>
//<mdm:pendingChannelUpdates>876</mdm:pendingChannelUpdates>
//<mdm:heldChannelUpdates>0</mdm:heldChannelUpdates>
//<mdm:endDatedRecords>9</mdm:endDatedRecords>
//</mdm:Universe>
@Type.Element(name = "UniverseSummary")
public class UniverseSummary implements Type{
	@Type.Identifier
	private String guid;
	@Type.Property(name = "Name", contentType = ContentType.String)
	private String name;	
	@Type.Property(name = "ID", contentType = ContentType.String)
	private String id;	
	@Type.Property(name = "Golden Records", contentType = ContentType.Number)
	private long goldenRecords;
	@Type.Property(name = "Quarantined Records", contentType = ContentType.Number)
	private long quarantinedRecords;
	@Type.Property(name = "Pending Batches", contentType = ContentType.Number)
	private long pendingBatches;
	@Type.Property(name = "Pending Batch Records", contentType = ContentType.Number)
	private long pendingBatchRecords;
	@Type.Property(name = "Pending Channel Updates", contentType = ContentType.Number)
	private long pendingChannelUpdates;
	@Type.Property(name = "Held Channel Updates", contentType = ContentType.Number)
	private long heldChannelUpdates;
	@Type.Property(name = "End Dated Records", contentType = ContentType.Number)
	private long endDatedRecords;
	
	public UniverseSummary()
	{		
	}
	
	public UniverseSummary(Node document)
	{
		this.guid=UUID.randomUUID().toString();
		this.name=document.selectSingleNode("@name").getText();
		this.id=document.selectSingleNode("@id").getText();
		this.goldenRecords=Integer.parseInt(document.selectSingleNode("mdm:goldenRecords").getText());
		this.quarantinedRecords=Integer.parseInt(document.selectSingleNode("mdm:quarantinedRecords").getText());
		this.pendingBatches=Integer.parseInt(document.selectSingleNode("mdm:pendingBatches").getText());
		this.pendingBatchRecords=Integer.parseInt(document.selectSingleNode("mdm:pendingBatchRecords").getText());
		this.pendingChannelUpdates=Integer.parseInt(document.selectSingleNode("mdm:pendingChannelUpdates").getText());
		this.heldChannelUpdates=Integer.parseInt(document.selectSingleNode("mdm:heldChannelUpdates").getText());
		this.endDatedRecords=Integer.parseInt(document.selectSingleNode("mdm:endDatedRecords").getText());
	}

	public String getGuid() {
		return guid;
	}
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public long getGoldenRecords() {
		return goldenRecords;
	}

	public long getQuarantinedRecords() {
		return quarantinedRecords;
	}

	public long getPendingBatches() {
		return pendingBatches;
	}

	public long getPendingBatchRecords() {
		return pendingBatchRecords;
	}

	public long getPendingChannelUpdates() {
		return pendingChannelUpdates;
	}

	public long getHeldChannelUpdates() {
		return heldChannelUpdates;
	}

	public long getEndDatedRecords() {
		return endDatedRecords;
	}

	public static List<UniverseSummary> getList(List<Node> list)
	{
	    List<UniverseSummary> items = new ArrayList<>();
	    for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
	    	UniverseSummary item = new UniverseSummary(iter.next());
	        items.add(item);
	    }	
	    return items;
	}
}
