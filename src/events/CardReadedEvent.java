package events;

import java.math.BigInteger;

public class CardReadedEvent {
	
	private BigInteger card;
	private int PIN;
	
	public CardReadedEvent(BigInteger card, int PIN) {
		this.setCard(card);
		this.setPIN(PIN);
		
	}

	public BigInteger getCard() {
		return card;
	}

	public void setCard(BigInteger card) {
		this.card = card;
	}

	public int getPIN() {
		return PIN;
	}

	public void setPIN(int PIN) {
		this.PIN = PIN;
	}

}
