package com.manywho.services.mdm.actions.mdmplatform.ignorePotentialDuplicateForSourceEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.dom4j.Node;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

@Type.Element(name = "MatchResolveResponse")
public class MatchResolveResponse implements Type{
	@Type.Identifier
	private String guid;

	@Type.Property(name = "Success", contentType = ContentType.Boolean)
	private String success;
	
	@Type.Property(name = "ID", contentType = ContentType.String)
	private String id;
	
	@Type.Property(name = "Updated Date", contentType = ContentType.DateTime)
	private Date updatedDate;
	
	@Type.Property(name = "State", contentType = ContentType.String)
	private String state;
	
	@Type.Property(name = "State Detail", contentType = ContentType.String)
	private String stateDetail;
	
//	<mdm:MatchResolveResponse xmlns:mdm="http://mdm.api.platform.boomi.com/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
//	  <mdm:success>true</mdm:success>
//	  <mdm:transaction id="6dcec710-63f9-48db-9b1b-0409237b6858" updatedDate="2016-12-08T18:39:25.178Z" state="QUARANTINED" stateDetail="REQUIRES_APPROVAL"/>
//	</mdm:MatchResolveResponse>
	public MatchResolveResponse(Node document)
	{
		this.guid=UUID.randomUUID().toString();
		this.success=document.selectSingleNode("mdm:MatchResolveResponse/mdm:success").getText();
		this.id=document.selectSingleNode("mdm:MatchResolveResponse/mdm:transaction/@id").getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		try {
			this.updatedDate=sdf.parse(document.selectSingleNode("mdm:MatchResolveResponse/mdm:transaction/@updatedDate").getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.state=document.selectSingleNode("mdm:MatchResolveResponse/mdm:transaction/@state").getText();
		this.stateDetail=document.selectSingleNode("mdm:MatchResolveResponse/mdm:transaction/@stateDetail").getText();
	}

	public String getGuid() {
		return guid;
	}

	public String getSuccess() {
		return success;
	}

	public String getId() {
		return id;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public String getState() {
		return state;
	}

	public String getStateDetail() {
		return stateDetail;
	}
}
