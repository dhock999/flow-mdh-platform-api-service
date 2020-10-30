package com.manywho.services.mdm.actions.mdmplatform.approveQuarantinedEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.dom4j.Node;
import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

@Type.Element(name = "ApproveResponse")
public class ApproveResponse implements Type{

	@Type.Identifier
	private String guid;
	
	@Type.Property(name="Success", contentType=ContentType.Boolean)
	private Boolean success;
	
	@Type.Property(name="State Detail", contentType=ContentType.String)
	private String stateDetail;

	@Type.Property(name="State", contentType=ContentType.String)
	private String state;

	@Type.Property(name="Updated Date", contentType=ContentType.DateTime)
	private Date updatedDate;

	@Type.Property(name="ID", contentType=ContentType.String)
	private String id;

	public ApproveResponse()
	{		
	}
	
	public String getGuid() {
		return guid;
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getStateDetail() {
		return stateDetail;
	}

	public String getState() {
		return state;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public String getId() {
		return id;
	}

//	<mdm:ApproveResponse xmlns:mdm="http://mdm.api.platform.boomi.com/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
//	  <mdm:success>true</mdm:success>
//	  <mdm:transaction stateDetail="CREATED" state="COMPLETED" updatedDate="2016-12-13T14:51:39.201Z" id="39cf510e-460b-4857-b615-70b63844e84e"/>
//	</mdm:ApproveResponse>
	
	public ApproveResponse(Node document)
	{
		this.guid=UUID.randomUUID().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		this.success=Boolean.parseBoolean(document.selectSingleNode("//mdm:success").getText());
		this.state=document.selectSingleNode("//@state").getText();
		this.stateDetail=document.selectSingleNode("//@stateDetail").getText();
		try {
			this.updatedDate=sdf.parse(document.selectSingleNode("//@updatedDate").getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.id=document.selectSingleNode("//@id").getText();
	}
}
