package com.manywho.services.mdm.actions.mdmplatform.queryTransactions;

import java.util.List;

import javax.inject.Inject;

import org.dom4j.Document;
import org.dom4j.Node;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.mdm.AtomsphereAPI;
import com.manywho.services.mdm.ServiceConfiguration;
import com.manywho.services.mdm.actions.mdmplatform.commitStagedEntities.StagingActionRequest;

public class QueryTransactionsCommand implements ActionCommand<ServiceConfiguration, QueryTransactions, QueryTransactions.Inputs, QueryTransactions.Outputs>{

	AuthenticatedWho user;
    @Inject
    public QueryTransactionsCommand(AuthenticatedWho user) 
    {
    	this.user=user;
    }
    
	@Override
	public ActionResponse<QueryTransactions.Outputs> execute(ServiceConfiguration configuration, ServiceRequest request,
			QueryTransactions.Inputs input) {

		String resource = String.format("%s/universes/%s/transactions", input.getRepositoryID(), input.getUniverseID());
		String payload = input.getTransactionFilter().getRequestXML();
		Document document = AtomsphereAPI.executeAPIXML(configuration, "repositories", "POST", resource, payload);
		List<Transaction> transactions = Transaction.getList(document.selectSingleNode("mdm:TransactionQueryResponse"));
		Node node = document.selectSingleNode("mdm:TransactionQueryResponse/@offsetToken");
		String offsetToken="";
		if (node!=null)
			offsetToken = node.getText();
		node = document.selectSingleNode("mdm:TransactionQueryResponse/@totalCount");
		long totalCount=0;
		if (node!=null)
			totalCount = Long.parseLong(node.getText());
		node = document.selectSingleNode("mdm:TransactionQueryResponse/@resultCount");
		long resultCount=0;
		if (node!=null)
			resultCount = Long.parseLong(node.getText());;
		return new ActionResponse<>(new QueryTransactions.Outputs(transactions, offsetToken, totalCount, resultCount));
	}
}
