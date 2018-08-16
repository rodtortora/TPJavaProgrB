package events;

import java.math.BigInteger;

public class CardReadedEvent {
	
	private BigInteger card;
	
	public CardReadedEvent(BigInteger card) {
		this.setCard(card);		
	}

	public BigInteger getCard() {
		return card;
	}

	public void setCard(BigInteger card) {
		this.card = card;
	}

}
