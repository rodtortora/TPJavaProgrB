package events;

import java.math.BigInteger;

public class MoneyExtractionEvent {

	private BigInteger cantidadDinero;
	
	public MoneyExtractionEvent(BigInteger cantidadDinero) {
		this.setCantidadDinero(cantidadDinero);
	}

	public BigInteger getCantidadDinero() {
		return cantidadDinero;
	}

	public void setCantidadDinero(BigInteger cantidadDinero) {
		this.cantidadDinero = cantidadDinero;
	}

}
