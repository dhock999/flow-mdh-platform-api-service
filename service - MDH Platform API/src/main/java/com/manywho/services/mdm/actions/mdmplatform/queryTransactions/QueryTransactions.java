package com.manywho.services.mdm.actions.mdmplatform.queryTransactions;

import java.util.List;
import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name="Query Transactions", summary = "The Query Transactions operation sends a query of the transactions for a specified universe (domain) hosted in a specified repository under a specified account accessible to the authenticating user.", uri="/mdm/queryTransactions")
public class QueryTransactions {
	public static class Inputs{
	    @Action.Input(name = "Repository ID", contentType = ContentType.String, required = true)
	    private String repositoryID;

		@Action.Input(name = "Universe ID", contentType = ContentType.String, required = true)
	    private String universeID;

		@Action.Input(name = "Transaction Query Filters", contentType = ContentType.Object, required = true)
	    private TransactionQueryRequest transactionFilter;

		public String getRepositoryID() {
			return repositoryID;
		}

		public String getUniverseID() {
			return universeID;
		}
		
		public TransactionQueryRequest getTransactionFilter() {
			return transactionFilter;
		}
	}
	
	public static class Outputs {
		@Action.Output(name="Transactions", contentType=ContentType.List)
		private List<Transaction> transactions;
		
		@Action.Output(name="Offset Token", contentType=ContentType.String)
		private String offsetToken;

		@Action.Output(name="Total Count", contentType=ContentType.Number)
		private long totalCount;

		@Action.Output(name="Result Count", contentType=ContentType.Number)
		private long resultCount;

		public Outputs(List<Transaction> transactions, String offsetToken, long totalCount, long resultCount)
		{
			this.transactions=transactions;
			this.offsetToken=offsetToken;
			this.totalCount=totalCount;
			this.resultCount=resultCount;
		}
	}
}
