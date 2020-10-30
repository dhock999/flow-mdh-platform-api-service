package com.boomi.execution;

import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;
import com.manywho.services.mdm.actions.mdmplatform.getGoldenRecordForSourceEntity.GoldenRecord;

class GoldenRecordTest {

	String expectedXML = "<mdm:Record updatedDate=\"02-05-2014T08:44:47.000-0400\" createdDate=\"04-23-2012T14:30:26.000-0400\" \r\n" + 
			"  recordId=\"d5742c16-5318-4ba7-8815-3267a7a55358\" xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" \r\n" + 
			"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"   <mdm:data>\r\n" + 
			"      <contact>\r\n" + 
			"         <id>d5742c16-5318-4ba7-8815-3267a7a55358</id>\r\n" + 
			"         <name>bob</name>\r\n" + 
			"         <city>berwyn</city>\r\n" + 
			"         <email>bob@gmail.com</email>\r\n" + 
			"      </contact>\r\n" + 
			"   </mdm:data>\r\n" + 
			"</mdm:Record> ";
	@Test
	void testResponse() throws DocumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		Document document = DocumentHelper.parseText(expectedXML);
		GoldenRecord item = new GoldenRecord(document);
		assertEquals("d5742c16-5318-4ba7-8815-3267a7a55358", item.getRecordId());
		assertEquals("xxx", item.getData());
	}
}
