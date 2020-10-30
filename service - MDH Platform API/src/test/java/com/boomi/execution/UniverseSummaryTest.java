package com.boomi.execution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.junit.jupiter.api.Test;

import com.manywho.services.mdm.actions.mdmplatform.RepositorySummary;
import com.manywho.services.mdm.actions.mdmplatform.UniverseSummary;
import com.manywho.services.mdm.actions.mdmplatform.queryStagedEntities.StagingEntry;

class UniverseSummaryTest {

	String expectedXML = "<mdm:Universe name=\"Accounts\" id=\"04ea04cc-9df8-4004-bc00-a21b7dede1bb\" xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" \r\n" + 
			"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"   <mdm:goldenRecords>811</mdm:goldenRecords>\r\n" + 
			"   <mdm:quarantinedRecords>21</mdm:quarantinedRecords>\r\n" + 
			"   <mdm:pendingBatches>2</mdm:pendingBatches>\r\n" + 
			"   <mdm:pendingBatchRecords>135</mdm:pendingBatchRecords>\r\n" + 
			"   <mdm:pendingChannelUpdates>876</mdm:pendingChannelUpdates>\r\n" + 
			"   <mdm:heldChannelUpdates>0</mdm:heldChannelUpdates>\r\n" + 
			"   <mdm:endDatedRecords>9</mdm:endDatedRecords>\r\n" + 
			"</mdm:Universe>";
	
	@Test
	void testUniverseSummary() throws DocumentException {
		Document expected = DocumentHelper.parseText(expectedXML);
		UniverseSummary universe = new UniverseSummary(expected.selectSingleNode("mdm:Universe"));
		assertEquals("Accounts", universe.getName());
		assertEquals("04ea04cc-9df8-4004-bc00-a21b7dede1bb", universe.getId());	
	}
}
