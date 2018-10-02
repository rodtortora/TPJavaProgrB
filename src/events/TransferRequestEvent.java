package events;

import java.math.BigInteger;

public class TransferRequestEvent {
	private BigInteger cbuDestino;

	public TransferRequestEvent() {
		this.setCbuDestino(cbuDestino);
	}

	public BigInteger getCbuDestino() {
		return cbuDestino;
	}

	public void setCbuDestino(BigInteger cbuDestino) {
		this.cbuDestino = cbuDestino;
	}

}
