package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaSueldo extends CajaAhorro {
	
	private String cuitEmpleador;
	
	public CuentaSueldo(BigInteger cBU, BigDecimal saldo, BigDecimal tasaOtroBanco, String cuitEmpleador) {
		super(cBU, saldo, tasaOtroBanco);
		this.setCuitEmpleador(cuitEmpleador);
	}
	
	public String getCuitEmpleador() {
		return cuitEmpleador;
	}

	public void setCuitEmpleador(String cuitEmpleador) {
		this.cuitEmpleador = cuitEmpleador;
	}

}
