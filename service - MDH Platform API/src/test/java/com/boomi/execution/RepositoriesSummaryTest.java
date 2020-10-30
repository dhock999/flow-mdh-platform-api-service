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

class RepositoriesSummaryTest {

	String expectedXML = "<mdm:Repositories xmlns:mdm=\"http://mdm.api.platform.boomi.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"   <mdm:Repository name=\"MyCompany Master\" id=\"dbe797ff-6d6f-44e9-acd9-d77494a44b15\">\r\n" + 
			"      <mdm:goldenRecords>832</mdm:goldenRecords>\r\n" + 
			"      <mdm:quarantinedRecords>30</mdm:quarantinedRecords>\r\n" + 
			"      <mdm:pendingBatches>2</mdm:pendingBatches>\r\n" + 
			"      <mdm:pendingBatchRecords>135</mdm:pendingBatchRecords>\r\n" + 
			"      <mdm:pendingChannelUpdates>893</mdm:pendingChannelUpdates>\r\n" + 
			"      <mdm:heldChannelUpdates>12</mdm:heldChannelUpdates>\r\n" + 
			"      <mdm:endDatedRecords>11</mdm:endDatedRecords>\r\n" + 
			"      <mdm:Universe name=\"Accounts\" id=\"04ea04cc-9df8-4004-bc00-a21b7dede1bb\">\r\n" + 
			"         <mdm:goldenRecords>811</mdm:goldenRecords>\r\n" + 
			"         <mdm:quarantinedRecords>21</mdm:quarantinedRecords>\r\n" + 
			"         <mdm:pendingBatches>2</mdm:pendingBatches>\r\n" + 
			"         <mdm:pendingBatchRecords>135</mdm:pendingBatchRecords>\r\n" + 
			"         <mdm:pendingChannelUpdates>876</mdm:pendingChannelUpdates>\r\n" + 
			"         <mdm:heldChannelUpdates>0</mdm:heldChannelUpdates>\r\n" + 
			"         <mdm:endDatedRecords>9</mdm:endDatedRecords>\r\n" + 
			"      </mdm:Universe>\r\n" + 
			"      <mdm:Universe name=\"Employees\" id=\"1d969a54-49e5-4f75-974a-85869280873d\">\r\n" + 
			"         <mdm:goldenRecords>21</mdm:goldenRecords>\r\n" + 
			"         <mdm:quarantinedRecords>9</mdm:quarantinedRecords>\r\n" + 
			"         <mdm:pendingBatches>0</mdm:pendingBatches>\r\n" + 
			"         <mdm:pendingBatchRecords>0</mdm:pendingBatchRecords>\r\n" + 
			"         <mdm:pendingChannelUpdates>17</mdm:pendingChannelUpdates>\r\n" + 
			"         <mdm:heldChannelUpdates>12</mdm:heldChannelUpdates>\r\n" + 
			"         <mdm:endDatedRecords>2</mdm:endDatedRecords>\r\n" + 
			"      </mdm:Universe>\r\n" + 
			"   </mdm:Repository>\r\n" + 
			"   <mdm:Repository name=\"MyCompany Test\" id=\"3093b444-0632-4bdc-98b6-27845860a40c\">\r\n" + 
			"      <mdm:goldenRecords>0</mdm:goldenRecords>\r\n" + 
			"      <mdm:quarantinedRecords>0</mdm:quarantinedRecords>\r\n" + 
			"      <mdm:pendingBatches>0</mdm:pendingBatches>\r\n" + 
			"      <mdm:pendingBatchRecords>0</mdm:pendingBatchRecords>\r\n" + 
			"      <mdm:pendingChannelUpdates>0</mdm:pendingChannelUpdates>\r\n" + 
			"      <mdm:heldChannelUpdates>0</mdm:heldChannelUpdates>\r\n" + 
			"      <mdm:endDatedRecords>0</mdm:endDatedRecords>\r\n" + 
			"      <mdm:Universe name=\"Vendors\" id=\"2d78de8c-3947-4cf9-96f8-8a088b67fb95\">\r\n" + 
			"         <mdm:goldenRecords>0</mdm:goldenRecords>\r\n" + 
			"         <mdm:quarantinedRecords>0</mdm:quarantinedRecords>\r\n" + 
			"         <mdm:pendingBatches>0</mdm:pendingBatches>\r\n" + 
			"         <mdm:pendingBatchRecords>0</mdm:pendingBatchRecords>\r\n" + 
			"         <mdm:pendingChannelUpdates>0</mdm:pendingChannelUpdates>\r\n" + 
			"         <mdm:heldChannelUpdates>0</mdm:heldChannelUpdates>\r\n" + 
			"         <mdm:endDatedRecords>0</mdm:endDatedRecords>\r\n" + 
			"      </mdm:Universe>\r\n" + 
			"   </mdm:Repository>\r\n" + 
			"</mdm:Repositories>";
	@Test
	void testRepositoryList() throws DocumentException {

		Document expected = DocumentHelper.parseText(expectedXML);
		Node root = expected.selectSingleNode("mdm:Repositories");
		List<RepositorySummary> repositories = RepositorySummary.getList(root.selectNodes("mdm:Repository"));
		assertEquals(2, repositories.size());
		assertEquals("dbe797ff-6d6f-44e9-acd9-d77494a44b15",repositories.get(0).getId());
		assertEquals(832, repositories.get(0).getGoldenRecords());
		assertEquals(30, repositories.get(0).getQuarantinedRecords());
		
		List<UniverseSummary> universes = repositories.get(0).getUniverses();
		assertEquals(2, universes.size());
		UniverseSummary universe = universes.get(0);
		assertEquals("Accounts", universe.getName());
		assertEquals("04ea04cc-9df8-4004-bc00-a21b7dede1bb", universe.getId());
		assertEquals(811, universe.getGoldenRecords());
		
	}
}
