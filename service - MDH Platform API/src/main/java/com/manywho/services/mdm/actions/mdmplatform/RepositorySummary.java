package com.manywho.services.mdm.actions.mdmplatform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.dom4j.Node;
import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

//<mdm:Repository name="MyCompany Master" id="dbe797ff-6d6f-44e9-acd9-d77494a44b15">
//<mdm:goldenRecords>832</mdm:goldenRecords>
//<mdm:quarantinedRecords>30</mdm:quarantinedRecords>
//<mdm:pendingBatches>2</mdm:pendingBatches>
//<mdm:pendingBatchRecords>135</mdm:pendingBatchRecords>
//<mdm:pendingChannelUpdates>893</mdm:pendingChannelUpdates>
//<mdm:heldChannelUpdates>12</mdm:heldChannelUpdates>
//<mdm:endDatedRecords>11</mdm:endDatedRecords>
//<mdm:Universe name="Accounts" id="04ea04cc-9df8-4004-bc00-a21b7dede1bb">
//   <mdm:goldenRecords>811</mdm:goldenRecords>
//   <mdm:quarantinedRecords>21</mdm:quarantinedRecords>
@Type.Element(name = "RepositorySummary")
public class RepositorySummary implements Type{
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
	@Type.Property(name = "Universes", contentType = ContentType.List)
	private List<UniverseSummary> universes;
	
	public RepositorySummary()
	{		
	}
	
	public RepositorySummary(Node document)
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
		this.universes = UniverseSummary.getList(document.selectNodes("mdm:Universe"));
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
	
	public List<UniverseSummary> getUniverses() {
		return universes;
	}

	public static List<RepositorySummary> getList(List<Node> list)
	{
	    List<RepositorySummary> items = new ArrayList<>();
	    for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
	    	RepositorySummary item = new RepositorySummary(iter.next());
	        items.add(item);
	    }	
	    return items;
	}
}
