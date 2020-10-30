package com.boomi.execution;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;
import com.manywho.services.mdm.actions.mdmplatform.approveQuarantinedEntry.ApproveResponse;

class ApproveResponseTest {


	String expectedXML = "	<mdm:ApproveResponse xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"	  <mdm:success>true</mdm:success>\r\n" + 
			"	  <mdm:transaction stateDetail=\"CREATED\" state=\"COMPLETED\" updatedDate=\"2016-12-13T14:51:39.201Z\" id=\"39cf510e-460b-4857-b615-70b63844e84e\"/>\r\n" + 
			"	</mdm:ApproveResponse>\r\n" + 
			"";
	@Test
	void testResponse() throws DocumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		Document document = DocumentHelper.parseText(expectedXML);
		ApproveResponse item = new ApproveResponse(document);
		assertEquals(true, item.getSuccess());
		assertEquals("CREATED", item.getStateDetail());
		assertEquals("COMPLETED", item.getState());
		assertEquals("2016-12-13T14:51:39.201Z", sdf.format(item.getUpdatedDate()));
		assertEquals("39cf510e-460b-4857-b615-70b63844e84e", item.getId());		
	}
}
