package com.sapient.transactions;

public class TransactionOperation {
	
	private String clientId;
	private String securityId;
	private String transactionDate;
	private String transactionType;
	private String priority;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	// calculate precessing fee of transaction
	public int processTransactionFee() {
		String priorityVal = this.priority.toString();
		String transType = this.transactionType.toString();
		if(priorityVal.contains("Y")) return 500;
		else if(priorityVal.contains("N")) 
			if(transType.contains("Sell and Withdraw")) return 100;
			else if(transType.contains("Buy and Deposit")) return 50;
		return 0;
	}
}
