package com.manywho.services.mdm.actions.mdmplatform.getGoldenRecordForSourceEntity;

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

//<mdm:Record endDate="02-05-2014T11:33:27.000-0400" updatedDate="02-05-2014T08:44:47.000-0400" createdDate="04-23-2012T14:30:26.000-0400" 
//recordId="d5742c16-5318-4ba7-8815-3267a7a55358" xmlns:mdm="http://mdm.api.platform.boomi.com/" 
//xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
// <mdm:data>
//    <contact>
//       <id>d5742c16-5318-4ba7-8815-3267a7a55358</id>
//       <name>bob</name>
//       <city>berwyn</city>
//       <email>bob@gmail.com</email>
//    </contact>
// </mdm:data>
//</mdm:Record> 

@Type.Element(name = "GoldenRecord")
public class GoldenRecord implements Type{
	@Type.Identifier
	private String guid;

	@Type.Property(name = "End Date", contentType = ContentType.DateTime)
	private Date endDate;	
	
	@Type.Property(name = "Created Date", contentType = ContentType.DateTime)
	private Date createdDate;	
	
	@Type.Property(name = "Updated Date", contentType = ContentType.DateTime)
	private Date updatedDate;	
		
	@Type.Property(name = "Record ID", contentType = ContentType.String)
	private String recordId;	
	
	@Type.Property(name = "Data", contentType = ContentType.String)
	private String data;	
	
	public GoldenRecord()
	{		
	}
	
	public GoldenRecord(Node document)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		document = document.selectSingleNode("mdm:Record");
		this.guid=UUID.randomUUID().toString();
		try {
			Node date = document.selectSingleNode("@createdDate");
			if (date!=null)
				this.createdDate = sdf.parse(date.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		try {
			Node date = document.selectSingleNode("@endDate");
			if (date!=null)
				this.endDate = sdf.parse(date.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		try {
			Node date = document.selectSingleNode("@createdDate");
			if (date!=null)
				this.createdDate = sdf.parse(date.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.recordId = document.selectSingleNode("@recordId").getText();
		this.data = document.selectSingleNode("mdm:data").selectSingleNode("*").asXML();
	}

	public String getGuid() {
		return guid;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public String getRecordId() {
		return recordId;
	}

	public String getData() {
		return data;
	}
	
}
