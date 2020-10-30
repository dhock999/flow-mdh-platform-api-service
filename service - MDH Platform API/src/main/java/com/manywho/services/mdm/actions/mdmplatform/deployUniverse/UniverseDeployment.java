package com.manywho.services.mdm.actions.mdmplatform.deployUniverse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.dom4j.Node;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

//<mdm:UniverseDeployment xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mdm="http://mdm.api.platform.boomi.com/">
//<mdm:id>456789ab-cdef-0123-4567-89abcdef0123</mdm:id>
//<mdm:universeId>01234567-89ab-cdef-0123-456789abcdef</mdm:universeId>
//<mdm:universeVersion>2</mdm:universeVersion>
//<mdm:status>PENDING</mdm:status>
//<mdm:deployDate>2020-02-08T16:36:16Z</mdm:deployDate>
//<mdm:url>https://platform.boomi.com/mdm/api/rest/v1/account-123456/universe/01234567-89ab-cdef-0123-456789abcdef/deployments/456789ab-cdef-0123-4567-89abcdef0123</mdm:url>
//</mdm:UniverseDeployment>
@Type.Element(name = "UniverseDeployment")
public class UniverseDeployment implements Type{
	@Type.Identifier
	private String guid;

	@Type.Property(name = "ID", contentType = ContentType.String)
	private String id;	
	
	@Type.Property(name = "Universe ID", contentType = ContentType.String)
	private String universeId;	
	
	@Type.Property(name = "Universe Version", contentType = ContentType.Number)
	private long universeVersion;	
	
	@Type.Property(name = "Status", contentType = ContentType.String)
	private String status;	
	
	@Type.Property(name = "Deploy Date", contentType = ContentType.DateTime)
	private Date deployDate;	
	
	@Type.Property(name = "URL", contentType = ContentType.String)
	private String url;	
	
	public UniverseDeployment()
	{		
	}
	
	public UniverseDeployment(Node document)
	{
		this.guid=UUID.randomUUID().toString();
		this.id=document.selectSingleNode("mdm:id").getText();
		this.universeId=document.selectSingleNode("mdm:universeId").getText();
		this.universeVersion=Integer.parseInt(document.selectSingleNode("mdm:universeVersion").getText());
		this.status=document.selectSingleNode("mdm:status").getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		try {
			this.deployDate=sdf.parse(document.selectSingleNode("mdm:deployDate").getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		this.url=document.selectSingleNode("mdm:url").getText();
	}

	public String getGuid() {
		return guid;
	}

	public String getId() {
		return id;
	}

	public String getUniverseId() {
		return universeId;
	}

	public long getUniverseVersion() {
		return universeVersion;
	}

	public String getStatus() {
		return status;
	}

	public Date getDeployDate() {
		return deployDate;
	}

	public String getUrl() {
		return url;
	}
}
