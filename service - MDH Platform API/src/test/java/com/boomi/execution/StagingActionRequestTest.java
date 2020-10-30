package com.boomi.execution;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionRequest;

class StagingActionRequestTest {

	String requestXML1 = "<mdm:StagingActionRequest xmlns:mdm=\"http://mdm.api.platform.boomi.com/\"" + 
			" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
			"<mdm:sourceId>SF</mdm:sourceId>" + 
			"<mdm:stagingAreaId>SFStaging</mdm:stagingAreaId>" + 
			"<mdm:filter>" + 
			"<mdm:stagedEntryId>5</mdm:stagedEntryId>" + 
			"<mdm:sourceEntityId>6</mdm:sourceEntityId>" + 
			"<mdm:entityResult>QUARANTINED.ENRICH_ERROR</mdm:entityResult>" + 
			"<mdm:createDateRelative>DAY</mdm:createDateRelative>" + 
			"</mdm:filter>" + 
			"</mdm:StagingActionRequest>";
	
	String requestXML2 = "<mdm:StagingActionRequest xmlns:mdm=\"http://mdm.api.platform.boomi.com/\"" + 
			" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + 
			"<mdm:sourceId>SF</mdm:sourceId>" + 
			"<mdm:stagingAreaId>SFStaging</mdm:stagingAreaId>" + 
			"<mdm:filter>" + 
			"<mdm:stagedEntryId>5</mdm:stagedEntryId>" + 
			"<mdm:sourceEntityId>6</mdm:sourceEntityId>" + 
			"<mdm:entityResult>QUARANTINED.ENRICH_ERROR</mdm:entityResult>" + 
			"<mdm:createDateFrom>2015-07-31T14:25:07</mdm:createDateFrom>" + 
			"<mdm:createDateTo>2016-07-31T14:25:07</mdm:createDateTo>" + 
			"</mdm:filter>" + 
			"</mdm:StagingActionRequest>";
	
	@Test
	void testGetRequestXML() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		StagingActionFilter filter = new StagingActionFilter();
		filter.setSourceId("SF");
		filter.setStagingAreaId("SFStaging");
		filter.setStagedEntryId("5");
		filter.setSourceEntityId("6");
		filter.setEntityResult("QUARANTINED.ENRICH_ERROR");
		filter.setCreateDateRelative("DAY");
		String actualRequest;
		actualRequest = StagingActionRequest.getRequestXML(filter);
//		CompareMatcher matcher = new CompareMatcher();
		assertThat(actualRequest, CompareMatcher.isIdenticalTo(requestXML1).ignoreWhitespace());
//		actualRequest = StagingActionRequest.getRequestXML("SF", "SFStaging", "5", "6", "QUARANTINED.ENRICH_ERROR", "", sdf.parse("2015-07-31T14:25:07"), sdf.parse("2016-07-31T14:25:07"));
//		assertEquals(requestXML2, actualRequest);
	}

}
