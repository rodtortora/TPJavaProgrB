package events;

import java.math.BigInteger;

public class BajaRequestEvent {
	
	private BigInteger nroTarjeta;

	public BajaRequestEvent(BigInteger nroTarjeta) {
		this.setNroTarjeta(nroTarjeta);
	}

	public BigInteger getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(BigInteger nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

}
