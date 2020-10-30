package com.boomi.execution;

import static org.junit.Assert.assertThat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;
import com.manywho.services.mdm.actions.mdmplatform.queryTransactions.TransactionQueryRequest;

class TransactionQueryRequestTest {

	String requestXML = "<mdm:TransactionQueryRequest xmlns:mdm=\"http://mdm.api.platform.boomi.com/\"\r\n" + 
			"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" offsetToken=\"abc\" limit=\"10\" includeEvents=\"true\">\r\n" + 
			"   <mdm:sourceId>netsuite</mdm:sourceId>\r\n" + 
			"   <mdm:startFromDate>2016-09-15T23:47:00</mdm:startFromDate>\r\n" + 
			"   <mdm:startToDate>2016-09-15T23:48:59</mdm:startToDate>\r\n" + 
			"</mdm:TransactionQueryRequest>";
	@Test
	void testGetRequestXML() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		String actualRequest="";
		TransactionQueryRequest request = new TransactionQueryRequest();
		request.setOffsetToken("abc");
		request.setLimit(10);
		request.setIncludeEvents(true);
		request.setSourceId("netsuite");
		request.setStartFromDate(sdf.parse("2016-09-15T23:47:00"));
		request.setStartToDate(sdf.parse("2016-09-15T23:48:59Z"));
		actualRequest = request.getRequestXML();
		assertThat(actualRequest, CompareMatcher.isIdenticalTo(requestXML).ignoreWhitespace());
	}
}
