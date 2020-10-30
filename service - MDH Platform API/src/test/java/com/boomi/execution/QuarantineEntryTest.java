package com.boomi.execution;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;

import com.manywho.services.mdm.actions.mdmplatform.approveQuarantinedEntry.ApproveResponse;
import com.manywho.services.mdm.actions.mdmplatform.getQuarantineEntry.QuarantineEntry;

class QuarantineEntryTest {
	String expectedXML = "<mdm:QuarantineEntry transactionId=\"01234567-89ab-cdef-0123-456789abcdef\" sourceId=\"salesforce\" entityId=\"2\" createdDate=\"2012-07-12T21:45:54Z\" \r\n" + 
			"  xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"   <mdm:cause>ENRICH_ERROR</mdm:cause>\r\n" + 
			"   <mdm:reason>At data quality step 'boomi-internal-sitest-priv_dev:::AddressVerification': [304] Address Not Found.</mdm:reason>\r\n" + 
			"   <mdm:entity>\r\n" + 
			"      <contact>\r\n" + 
			"         <id>2</id>\r\n" + 
			"         <name>jack</name>\r\n" + 
			"         <address_1>not home</address_1>\r\n" + 
			"         <city>berwyn</city>\r\n" + 
			"         <state>PA</state>\r\n" + 
			"         <zip>19312</zip>\r\n" + 
			"      </contact>\r\n" + 
			"   </mdm:entity>\r\n" + 
			"</mdm:QuarantineEntry>";
	@Test
	void testResponse() throws DocumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		Document document = DocumentHelper.parseText(expectedXML);
		QuarantineEntry item = new QuarantineEntry(document);
		assertEquals("01234567-89ab-cdef-0123-456789abcdef", item.getTransactionId());
		assertEquals("salesforce", item.getSourceID());
		assertEquals("2", item.getEntityId());
//		assertEquals("2016-12-13T14:51:39.201Z", sdf.format(item.getCreatedDate()));
		assertEquals("ENRICH_ERROR", item.getCause());		
		assertEquals("At data quality step 'boomi-internal-sitest-priv_dev:::AddressVerification': [304] Address Not Found.", item.getReason());	
		assertEquals("xxx]"
				+ "", item.getEntity());		
	}
}