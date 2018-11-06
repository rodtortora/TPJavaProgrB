package model;

import java.math.BigDecimal;

public class Tarifa {
		
	private int tipoTransaccion;
	private BigDecimal importeDebitar;
	private String tipoCuenta, aplicableA;
	private int aplicaAPartirDelMov;
	
	
	public Tarifa(int tipoTransaccion, String tipoCuenta, int aplicaAPartirDelMov, BigDecimal importeDebitar, String aplicableA) {
		this.setImporteDebitar(importeDebitar);
		this.setTipoTransaccion(tipoTransaccion);
		this.setTipoCuenta(tipoCuenta);
		this.setAplicaAPartirDelMov(aplicaAPartirDelMov);
		this.setImporteDebitar(importeDebitar);
		this.setAplicableA(aplicableA);
	}

	public int getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(int tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public BigDecimal importeDebitar() {
		return importeDebitar;
	}

	public void setImporteDebitar(BigDecimal importeDebitar) {
		this.importeDebitar = importeDebitar;
	}

	public String getAplicableA() {
		return aplicableA;
	}

	public void setAplicableA(String aplicableA) {
		this.aplicableA = aplicableA;
	}

	public int getAplicaAPartirDelMov() {
		return aplicaAPartirDelMov;
	}

	public void setAplicaAPartirDelMov(int aplicaAPartirDelMov) {
		this.aplicaAPartirDelMov = aplicaAPartirDelMov;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}
