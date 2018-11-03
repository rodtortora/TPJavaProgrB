package events;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TransferRequestEvent {
	private BigDecimal moneyAmmount;
	private BigInteger cbuDestino;

	public TransferRequestEvent(BigDecimal moneyAmount, BigInteger cbuDestino) {
		this.setCbuDestino(cbuDestino);
		this.setMoneyAmmount(moneyAmount);
	}

	public BigInteger getCbuDestino() {
		return cbuDestino;
	}

	public void setCbuDestino(BigInteger cbuDestino) {
		this.cbuDestino = cbuDestino;
	}

	public BigDecimal getMoneyAmmount() {
		return moneyAmmount;
	}

	public void setMoneyAmmount(BigDecimal moneyAmmount) {
		this.moneyAmmount = moneyAmmount;
	}

}
