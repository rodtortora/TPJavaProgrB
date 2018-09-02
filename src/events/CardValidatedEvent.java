package events;

import model.TarjetaATM;

public class CardValidatedEvent {
	
	private TarjetaATM card;
	
	public CardValidatedEvent(TarjetaATM card) {
		
		this.setCard(card);
		
	}

	public TarjetaATM getCard() {
		return card;
	}

	public void setCard(TarjetaATM card) {
		this.card = card;
	}

}
