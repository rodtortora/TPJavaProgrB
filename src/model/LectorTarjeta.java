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
		}
	}
	
	public void retenerTarjeta(BigInteger idTarjetaATM) {
		//TODO
		this.setTarjetaLeida(null);		
	}	
	
}
