package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Transaction {
	private Calendar fechaTransaccion;
	private ArrayList<Tarifa> tarifas;
	private BigDecimal moneyAmount;
	private String notas;
	
	public Transaction(Calendar fechaTransaccion, ArrayList<Tarifa> tarifasTransaccion, BigDecimal moneyAmount) {
		this.setFechaTransaccion(fechaTransaccion);
		this.setTarifas(tarifasTransaccion);
		this.setMoneyAmount(moneyAmount);
		this.setNotas(notas);
	}

	public Calendar getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Calendar fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public ArrayList<Tarifa> getTarifas() {
		return tarifas;
	}

	public void setTarifas(ArrayList<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public BigDecimal getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(BigDecimal moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

}
