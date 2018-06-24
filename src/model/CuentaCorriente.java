package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaCorriente extends Cuenta {
	private BigDecimal descubierto;
	
	public CuentaCorriente(BigInteger cBU, BigDecimal saldo, BigDecimal descubierto) {
		super(cBU, saldo);
		this.setDescubierto(descubierto);
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getDescubierto() {
		return descubierto;
	}

	public void setDescubierto(BigDecimal descubierto) {
		this.descubierto = descubierto;
	}



}
