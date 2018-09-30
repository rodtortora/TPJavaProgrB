package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Transaction {
	private Calendar fechaTransaccion;
	private BigDecimal moneyAmount;
	private int tipoTransaccion;
	
	public Transaction(Calendar fechaTransaccion, BigDecimal moneyAmount, int tipoTransaccion) {
		this.setFechaTransaccion(fechaTransaccion);
		this.setMoneyAmount(moneyAmount);
		this.setTipoTransaccion(tipoTransaccion);
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

}
