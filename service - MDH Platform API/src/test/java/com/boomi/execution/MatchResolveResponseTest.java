package com.boomi.execution;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;
import com.manywho.services.mdm.actions.mdmplatform.ignorePotentialDuplicateForSourceEntity.MatchResolveResponse;

class MatchResolveResponseTest {

	String expectedXML = "<mdm:MatchResolveResponse xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"  <mdm:success>true</mdm:success>\r\n" + 
			"  <mdm:transaction id=\"6dcec710-63f9-48db-9b1b-0409237b6858\" updatedDate=\"2016-12-08T18:39:25Z\" state=\"QUARANTINED\" stateDetail=\"REQUIRES_APPROVAL\"/>\r\n" + 
			"</mdm:MatchResolveResponse>";
	@Test
	void testResponse() throws DocumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		Document document = DocumentHelper.parseText(expectedXML);
		MatchResolveResponse item = new MatchResolveResponse(document);
		assertEquals(true, item.getSuccess());
		assertEquals("REQUIRES_APPROVAL", item.getStateDetail());
		assertEquals("QUARANTINED", item.getState());
		assertEquals("2016-12-13T14:51:39Z", sdf.format(item.getUpdatedDate()));
		assertEquals("6dcec710-63f9-48db-9b1b-0409237b6858", item.getId());		
	}
}
