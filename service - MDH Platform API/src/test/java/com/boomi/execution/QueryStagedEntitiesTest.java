package com.boomi.execution;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;
import org.xmlunit.matchers.CompareMatcher;

import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionFilter;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionRequest;
import com.manywho.services.mdm.actions.mdmplatform.ignorePotentialDuplicateForSourceEntity.MatchResolveActionCommand;
import com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities.EntityResultSummary;
import com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities.StagingEntry;
import com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities.StagingQueryRequest;

class QueryStagedEntitiesTest {
	String responseXML="<mdm:StagingQueryResponse titleFieldName=\"Last Name\" totalCount=\"5\" resultCount=\"5\" xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"   <mdm:StagingEntry result=\"COMPLETED.CREATED\" sourceEntityId=\"35\" createdDate=\"2015-07-27T20:01:41Z\" titleFieldValue=\"Diller\" stagedEntryId=\"15\"/>\r\n" + 
			"   <mdm:StagingEntry result=\"QUARANTINED.POSSIBLE_DUPLICATE\" sourceEntityId=\"34\" createdDate=\"2015-07-21T19:35:40Z\" titleFieldValue=\"Baker\" stagedEntryId=\"14\"/>\r\n" + 
			"   <mdm:StagingEntry result=\"QUARANTINED.POSSIBLE_DUPLICATE\" sourceEntityId=\"33\" createdDate=\"2015-07-21T19:35:40Z\" titleFieldValue=\"Baker\" stagedEntryId=\"13\"/>\r\n" + 
			"   <mdm:StagingEntry result=\"QUARANTINED.AMBIGUOUS_MATCH\" sourceEntityId=\"32\" createdDate=\"2015-07-21T19:35:40Z\" titleFieldValue=\"Baker\" stagedEntryId=\"12\"/>\r\n" + 
			"   <mdm:StagingEntry result=\"QUARANTINED.POSSIBLE_DUPLICATE\" sourceEntityId=\"31\" createdDate=\"2015-07-21T19:35:40Z\" titleFieldValue=\"Baker\" stagedEntryId=\"11\"/>\r\n" + 
			"   <mdm:StagingAreaSummary>\r\n" + 
			"      <mdm:entityResultSummary count=\"3\" name=\"QUARANTINED.POSSIBLE_DUPLICATE\"/>\r\n" + 
			"      <mdm:entityResultSummary count=\"1\" name=\"COMPLETED.CREATED\"/>\r\n" + 
			"      <mdm:entityResultSummary count=\"1\" name=\"QUARANTINED.AMBIGUOUS_MATCH\"/>\r\n" + 
			"   </mdm:StagingAreaSummary>\r\n" + 
			"</mdm:StagingQueryResponse>";
	
	String requestXML = "<mdm:StagingQueryRequest xmlns:mdm=\"http://mdm.api.platform.boomi.com/\"\r\n" + 
			"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" includeSummary=\"true\" includeRecords=\"true\" offsetToken=\"\" limit=\"10\">\r\n" + 
			"   <mdm:sourceId>SF</mdm:sourceId>\r\n" + 
			"   <mdm:stagingAreaId>SFStaging</mdm:stagingAreaId>\r\n" + 
			"   <mdm:filter>\r\n" + 
			"      <mdm:sourceEntityId>6</mdm:sourceEntityId>\r\n" + 
			"      <mdm:entityResult>QUARANTINED.ENRICH_ERROR</mdm:entityResult>\r\n" + 
			"      <mdm:createDateRelative>DAY</mdm:createDateRelative>\r\n" + 
			"   </mdm:filter>\r\n" + 
			"</mdm:StagingQueryRequest>";
	
	@Test
	void testGetEntityResultSummaryList() throws DocumentException {

		Document document = DocumentHelper.parseText(responseXML);
		List<EntityResultSummary> items = EntityResultSummary.getList(document.selectSingleNode("mdm:StagingQueryResponse"));
		assertEquals(3, items.size());
		assertEquals(3, items.get(0).getCount());
		assertEquals("QUARANTINED.POSSIBLE_DUPLICATE", items.get(0).getName());
	}
	
	@Test
	void testGetStagingEntryList() throws DocumentException {

		Document document = DocumentHelper.parseText(responseXML);
		List<StagingEntry> items = StagingEntry.getList(document.selectSingleNode("mdm:StagingQueryResponse"));
		assertEquals(5, items.size());
		assertEquals("COMPLETED.CREATED", items.get(0).getResult());
		assertEquals(35, items.get(0).getSourceEntityId());
		assertEquals("2015-07-27T20:01:41Z", items.get(0).getCreatedDate());
		assertEquals("Diller", items.get(0).getTitleFieldValue());
		assertEquals(15, items.get(0).getStagedEntryId());
	}
	
	@Test
	void testGetRequestXML() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		StagingActionFilter filter = new StagingActionFilter();
		filter.setSourceId("SF");
		filter.setStagingAreaId("SFStaging");
//TODO does query support setStagedEntryId? It isn't in the doc
//		filter.setStagedEntryId("5");
		filter.setSourceEntityId("6");
		filter.setEntityResult("QUARANTINED.ENRICH_ERROR");
		filter.setCreateDateRelative("DAY");
		String actualRequest;
		actualRequest = StagingQueryRequest.getRequestXML(true, true, "", 10, filter);
		assertThat(actualRequest, CompareMatcher.isIdenticalTo(requestXML).ignoreWhitespace());		
	}
}
