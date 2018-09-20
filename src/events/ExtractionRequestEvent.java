package events;

import java.math.BigInteger;

public class ExtractionRequestEvent {
	
	private BigInteger moneyAmount;

	public ExtractionRequestEvent(BigInteger moneyAmount) {
		this.setMoneyAmount(moneyAmount);
	}

	public BigInteger getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(BigInteger moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

}
