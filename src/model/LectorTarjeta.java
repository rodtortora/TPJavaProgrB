package model;

import java.math.BigInteger;

public class LectorTarjeta {
	private BigInteger tarjetaLeida;

	public BigInteger getTarjetaLeida() {
		return tarjetaLeida;
	}
	public void setTarjetaLeida(BigInteger tarjetaLeida) {
		this.tarjetaLeida = tarjetaLeida;
	}
	public void expulsarTarjeta() {
		if (this.getTarjetaLeida() != null) {
			this.setTarjetaLeida(null);
			System.out.println("Retire la tarjeta"); // TODO: menu de retiro de tarjeta
		}
	}
	
	public void retenerTarjeta(BigInteger idTarjetaATM) {
		System.out.println("Tarjeta retenida por el cajero"); // TODO: menu de tarjeta retenida
		this.setTarjetaLeida(null);		
	}	
	
}
