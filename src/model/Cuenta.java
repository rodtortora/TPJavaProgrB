package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Cuenta {
	private BigInteger CBU;
	private BigDecimal saldo, tasaExtraccOtroBanco, mantenimientoMensual, descubierto, tasaExtraccion, limiteExtraccion;
	private int limiteExtraccionesSC; // Cantidad extracciones sin cargo

	
	public Cuenta(BigInteger cBU, BigDecimal saldo) {
		CBU = cBU;
		this.saldo = saldo;
		this.limiteExtraccion = saldo;
	}
	
	public BigInteger getCBU() {
		return CBU;
	}

	public void setCBU(BigInteger CBU) {
		this.CBU = CBU;
	}

	public BigDecimal getSaldo() { // Saldo total
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getMantenimientoMensual() {
		return mantenimientoMensual;
	}

	public void setMantenimientoMensual(BigDecimal mantenimientoMensual) {
		this.mantenimientoMensual = mantenimientoMensual;
	}
	public BigDecimal getTasaExtraccOtroBanco() {
		return tasaExtraccOtroBanco;
	}

	public void setTasaExtraccOtroBanco(BigDecimal tasaExtraccOtroBanco) {
		this.tasaExtraccOtroBanco = tasaExtraccOtroBanco;
	}
	
	public BigDecimal getDescubierto() {
		return descubierto;
	}

	public void setDescubierto(BigDecimal descubierto) {
		this.descubierto = descubierto;
	}

	public int getLimiteExtraccionesSC() {
		return limiteExtraccionesSC;
	}

	public void setLimiteExtraccionesSC(int limiteExtraccionesSC) {
		this.limiteExtraccionesSC = limiteExtraccionesSC;
	}

	public BigDecimal getTasaExtraccion() {
		return tasaExtraccion;
	}

	public void setTasaExtraccion(BigDecimal tasaExtraccion) {
		this.tasaExtraccion = tasaExtraccion;
	}

	public BigDecimal getLimiteExtraccion() {
		return limiteExtraccion;
	}

	public void setLimiteExtraccion(BigDecimal limiteExtraccion) {
		this.limiteExtraccion = limiteExtraccion;
	}

	public String tipoCta() {
		return this.toString();
	}
	
}
