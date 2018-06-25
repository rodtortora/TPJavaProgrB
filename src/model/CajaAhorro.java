package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CajaAhorro extends Cuenta {
	
	public CajaAhorro(BigInteger cBU, BigDecimal saldo, BigDecimal tasaOtroBanco) {
		super(cBU, saldo);
		this.setTasaOtroBanco(tasaOtroBanco);
	}


	@Override
	public String toString() {
		return "CajaAhorro";
	}
}
