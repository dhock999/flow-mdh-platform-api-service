package com.manywho.services.mdm.actions.mdmplatform.getQuarantineEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.dom4j.Node;
import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

//<mdm:QuarantineEntry transactionId="01234567-89ab-cdef-0123-456789abcdef" sourceId="salesforce" entityId="2" createdDate="2012-07-12T21:45:54Z" 
//xmlns:mdm="http://mdm.api.platform.boomi.com/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
// <mdm:cause>ENRICH_ERROR</mdm:cause>
// <mdm:reason>At data quality step 'boomi-internal-sitest-priv_dev:::AddressVerification': [304] Address Not Found.</mdm:reason>
// <mdm:entity>
//    <contact>
//       <id>2</id>
//       <name>jack</name>
//       <address_1>not home</address_1>
//       <city>berwyn</city>
//       <state>PA</state>
//       <zip>19312</zip>
//    </contact>
// </mdm:entity>
//</mdm:QuarantineEntry>

@Type.Element(name = "QuarantineEntry")
public class QuarantineEntry implements Type{
	@Type.Identifier
	private String guid;

	@Type.Property(name = "Transaction ID", contentType = ContentType.String)
	private String transactionId;	
	
	@Type.Property(name = "Source ID", contentType = ContentType.String)
	private String sourceID;	
	
	@Type.Property(name = "Entity ID", contentType = ContentType.String)
	private String entityId;	
	
	@Type.Property(name = "Created Date", contentType = ContentType.DateTime)
	private Date createdDate;	
	
	@Type.Property(name = "Cause", contentType = ContentType.String)
	private String cause;	
	
	@Type.Property(name = "Reason", contentType = ContentType.String)
	private String reason;	
	
	@Type.Property(name = "Entity", contentType = ContentType.String)
	private String entity;	
	
	public QuarantineEntry()
	{		
	}
	
	public QuarantineEntry(Node doc)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
		Node document = doc.selectSingleNode("mdm:QuarantineEntry");
		if (document==null)
			throw new RuntimeException("mdm:QuarantineEntry not returned: " + doc.asXML());
		this.guid=UUID.randomUUID().toString();
		try {
			Node node = document.selectSingleNode("@createdDate");
			if (node!=null)
				this.createdDate = sdf.parse(node.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.transactionId = document.selectSingleNode("@transactionId").getText();
		this.sourceID = document.selectSingleNode("@sourceId").getText();
		this.entityId = document.selectSingleNode("@entityId").getText();
		this.cause = document.selectSingleNode("mdm:cause").getText();
		this.reason = document.selectSingleNode("mdm:reason").getText();
		this.entity = document.selectSingleNode("mdm:entity").selectSingleNode("*").asXML();
	}

	public String getGuid() {
		return guid;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getSourceID() {
		return sourceID;
	}

	public String getEntityId() {
		return entityId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getCause() {
		return cause;
	}

	public String getReason() {
		return reason;
	}

	public String getEntity() {
		return entity;
	}

}
