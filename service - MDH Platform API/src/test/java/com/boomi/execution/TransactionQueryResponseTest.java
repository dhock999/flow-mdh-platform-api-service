package com.boomi.execution;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;
import com.manywho.services.mdm.actions.mdmplatform.queryTransactions.Event;
import com.manywho.services.mdm.actions.mdmplatform.queryTransactions.Transaction;


class TransactionQueryResponseTest {

	String expectedXML = "<mdm:TransactionQueryResponse xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \r\n" + 
			" totalCount=\"25\" resultCount=\"10\" offsetToken=\"MzA=\">\r\n" + 
			"  <mdm:Transaction stateDetail=\"CREATED\" state=\"COMPLETED\" endDate=\"2016-09-15T23:47:45Z\" \r\n" + 
			"   startDate=\"2016-09-15T23:47:44Z\" sourceEntityId=\"810BD45E0281301D867DE638C1BC10\" sourceId=\"netsuite\" \r\n" + 
			"   id=\"c49ba11d-49d9-4c27-8daf-cc9c7df4650a\">\r\n" + 
			"    <mdm:event eventType=\"SUBMIT\" eventDate=\"2016-09-15T23:47:44Z\">\r\n" + 
			"      <mdm:description>Entity '810BD45E0281301D867DE638C1BC10' contributed by source 'Netsuite'.</mdm:description>\r\n" + 
			"    </mdm:event>\r\n" + 
			"    <mdm:event eventType=\"COMPLETE\" eventDate=\"2016-09-15T23:47:45Z\">\r\n" + 
			"      <mdm:description>Golden record 'c9d2ab00-edb3-4f93-b279-b9e45ad70cd5' created.</mdm:description>\r\n" + 
			"    </mdm:event>\r\n" + 
			"  </mdm:Transaction>\r\n" + 
			"  <mdm:Transaction stateDetail=\"UPDATED\" state=\"COMPLETED\" endDate=\"2016-09-15T23:48:14Z\" \r\n" + 
			"   startDate=\"2016-09-15T23:48:13Z\" sourceEntityId=\"92ACE56FA3924A2E978EF749D2CD2A\" sourceId=\"netsuite\" \r\n" + 
			"   id=\"5d98b284-353d-4737-aacf-cb5dfd95b9a8\">\r\n" + 
			"    <mdm:event eventType=\"SUBMIT\" eventDate=\"2016-09-15T23:48:13Z\">\r\n" + 
			"      <mdm:description>Entity '92ACE56FA3924A2E978EF749D2CD2A' contributed by source 'Netsuite'.</mdm:description>\r\n" + 
			"    </mdm:event>\r\n" + 
			"    <mdm:event eventType=\"COMPLETE\" eventDate=\"2016-09-15T23:48:14Z\">\r\n" + 
			"      <mdm:description>Golden record 'd5043312-199e-4e4d-9499-805eea06ac29' updated.</mdm:description>\r\n" + 
			"    </mdm:event>\r\n" + 
			"  </mdm:Transaction>\r\n" + 
			"</mdm:TransactionQueryResponse>";
	@Test
	void testResponse() throws DocumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Document document = DocumentHelper.parseText(expectedXML);
		List<Transaction> items = Transaction.getList(document.selectSingleNode("mdm:TransactionQueryResponse"));
		assertEquals(2, items.size());
		Transaction item = items.get(0);
		assertEquals("CREATED", item.getStateDetail());
		assertEquals("810BD45E0281301D867DE638C1BC10", item.getSourceEntityId());
		assertEquals("netsuite", item.getSourceId());
		assertEquals("COMPLETED", item.getState());
		assertEquals("2016-09-15T23:47:44Z", sdf.format(item.getStartDate()));
		assertEquals("2016-09-15T23:47:45Z", sdf.format(item.getEndDate()));
		assertEquals("39cf510e-460b-4857-b615-70b63844e84e", item.getId());	
		List<Event> events = item.getEvents();
		assertEquals(2, events.size());
		assertEquals("SUBMIT", events.get(0).getEventType());
		assertEquals("2016-09-15T23:47:44Z", sdf.format(events.get(0).getEventDate()));
		assertEquals("Entity '810BD45E0281301D867DE638C1BC10' contributed by source 'Netsuite'.", events.get(0).getDescription());
	}

}
