package model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Transaction {
	private Calendar fechaTransaccion;
	private BigDecimal moneyAmount;
	private int tipoTransaccion;
	private boolean esDebito;
	
	public Transaction(Calendar fechaTransaccion, BigDecimal moneyAmount, int tipoTransaccion, boolean esDebito) {
		this.setFechaTransaccion(fechaTransaccion);
		this.setMoneyAmount(moneyAmount);
		this.setTipoTransaccion(tipoTransaccion);
		this.setEsDebito(esDebito);
	}

	public Calendar getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Calendar fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public BigDecimal getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(BigDecimal moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public int getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(int tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public boolean isDebito() {
		return esDebito;
	}

	public void setEsDebito(boolean esDebito) {
		this.esDebito = esDebito;
	}

}
