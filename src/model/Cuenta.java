package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Cuenta {
	private BigInteger CBU;
	private BigDecimal saldo, tasaExtraccOtroBanco, mantenimientoMensual, tasaExtraccion, limiteExtraccionDiario, limiteDescubierto;
	private int limiteExtraccionesSinCargo; // Cantidad extracciones sin cargo
	private String tipoCuenta;

	/**
	 * Constructor
	 * @param CBU: nro de cuenta
	 * @param mantenimientoMensual
	 * @param saldo
	 * @param tasaExtraccOtroBanco: 
	 * @param limiteExtraccionesSC
	 * @param tasaExtraccion
	 * @param limiteExtraccionDiario
	 * @param limiteDescubierto
	 * @param tipoCta
	 */
	
	public Cuenta(BigInteger CBU, BigDecimal mantenimientoMensual, BigDecimal saldo, BigDecimal tasaExtraccOtroBanco,
			int limiteExtraccionesSC, BigDecimal tasaExtraccion, BigDecimal limiteExtraccionDiario, BigDecimal limiteDescubierto,
			String tipoCta) {
		this.setCBU(CBU);
		this.setSaldo(saldo);
		this.setMantenimientoMensual(mantenimientoMensual);
		this.setTasaExtraccOtroBanco(tasaExtraccOtroBanco);
		this.setLimiteExtraccionesSinCargo(limiteExtraccionesSC);
		this.setTasaExtraccion(tasaExtraccion);
		this.setLimiteExtraccionDiario(limiteExtraccionDiario);
		this.setLimiteDescubierto(limiteDescubierto);
		this.setTipoCuenta(tipoCta);

	}
	
	/**
	 * Getters & Setters
	 */
	
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

	public int getLimiteExtraccionesSinCargo() {
		return limiteExtraccionesSinCargo;
	}

	public void setLimiteExtraccionesSinCargo(int limiteExtraccionesSC) {
		this.limiteExtraccionesSinCargo = limiteExtraccionesSC;
	}

	public BigDecimal getTasaExtraccion() {
		return tasaExtraccion;
	}

	public void setTasaExtraccion(BigDecimal tasaExtraccion) {
		this.tasaExtraccion = tasaExtraccion;
	}

	public BigDecimal getLimiteExtraccionDiario() {
		return limiteExtraccionDiario;
	}

	public void setLimiteExtraccionDiario(BigDecimal limiteExtraccion) {
		this.limiteExtraccionDiario = limiteExtraccion;
	}

	public BigDecimal getLimiteDescubierto() {
		return limiteDescubierto;
	}

	public void setLimiteDescubierto(BigDecimal limiteDescubierto) {
		this.limiteDescubierto = limiteDescubierto;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	/**
	 * Functions
	 */
	
	@Override
	public String toString() {
		return this.CBU.toString() + " - " + this.getTipoCuenta();
	}
	
	
}
