package events;

import java.util.ArrayList;

import model.Transaction;

public class MovementsReturnedEvent {
	
	private ArrayList<Transaction> transactions;
	
	public MovementsReturnedEvent(ArrayList<Transaction> transactions) {
		this.setTransactions(transactions);
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

}
