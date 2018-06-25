package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaCorriente extends Cuenta {
/*	private BigDecimal descubierto, tasaExtraccion, limiteExtraccion;
	private int cantExtraccSinCargo;*/
	
	public CuentaCorriente(BigInteger cBU, BigDecimal saldo, BigDecimal descubierto, BigDecimal tasaExtraccion, int cantExtraccSinCargo) {
		super(cBU, saldo);
		this.setDescubierto(descubierto);
		this.setTasaExtraccion(tasaExtraccion);
		this.setLimiteExtraccionesSC(cantExtraccSinCargo);
		this.setTasaExtraccOtroBanco(tasaExtraccOtroBanco);
		// TODO Auto-generated constructor stub
	}

/*	public BigDecimal getDescubierto() {
		return descubierto;
	}

	public void setDescubierto(BigDecimal descubierto) {
		this.descubierto = descubierto;
	}

	public BigDecimal getTasaExtraccion() {
		return tasaExtraccion;
	}

	public void setTasaExtraccion(BigDecimal tasaExtraccion) {
		this.tasaExtraccion = tasaExtraccion;
	}

	public int getCantExtraccSinCargo() {
		return cantExtraccSinCargo;
	}

	public void setCantExtraccSinCargo(int cantExtraccSinCargo) {
		this.cantExtraccSinCargo = cantExtraccSinCargo;
	}

	public BigDecimal getLimiteExtraccion() {
		return limiteExtraccion;
	}*/

	public void setLimiteExtraccion() {
		BigDecimal limiteExtraccion = BigDecimal.valueOf(0);
		limiteExtraccion.add(getDescubierto());
		this.setLimiteExtraccion(limiteExtraccion);
	}



}
