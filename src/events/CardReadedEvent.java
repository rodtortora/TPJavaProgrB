package events;

import java.math.BigInteger;

import model.ATM;

public class CardReadedEvent {
	
	private BigInteger card;
	private ATM atm;
	
	public CardReadedEvent(BigInteger card) {
		this.setCard(card);		
	}

	public BigInteger getCard() {
		return card;
	}

	public void setCard(BigInteger card) {
		this.card = card;
	}
	
	public void setATM(ATM atm) {
		this.atm = atm;
	}

	public ATM getATM() {
		// TODO Auto-generated method stub
		return this.atm;
	}

}
