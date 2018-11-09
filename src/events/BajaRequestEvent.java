package events;

import java.math.BigInteger;

import model.Banco;

public class BajaRequestEvent {
	
	private Banco banco;
	private BigInteger nroTarjeta;

	public BajaRequestEvent(BigInteger nroTarjeta, Banco banco) {
		this.setNroTarjeta(nroTarjeta);
		this.setBanco(banco);
	}

	public BigInteger getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(BigInteger nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
