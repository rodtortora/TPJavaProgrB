package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaCorriente extends Cuenta {
/*	private BigDecimal descubierto, tasaExtraccion, limiteExtraccion;
	private int cantExtraccSinCargo;*/
	
	public CuentaCorriente(BigInteger cBU, BigDecimal saldo, BigDecimal descubierto, BigDecimal tasaExtraccion, int cantExtraccSinCargo, BigDecimal tasaExtraccOtroBanco) {
		super(cBU, saldo);
		this.setDescubierto(descubierto);
		this.setTasaExtraccion(tasaExtraccion);
		this.setLimiteExtraccionesSC(cantExtraccSinCargo);
		this.setTasaExtraccOtroBanco(tasaExtraccOtroBanco);
		// TODO Auto-generated constructor stub
	}

	public void setLimiteExtraccion() {
		super.setLimiteExtraccion();
		limiteExtraccion.add(getDescubierto());
	}



}
