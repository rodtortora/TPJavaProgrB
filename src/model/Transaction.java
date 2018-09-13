package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Transaction {
	private Date fechaYhora;
	

	public Transaction(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, ArrayList<Tarifa> tarifasTransaccion, BigDecimal cantidadExtraer) {
		this.setFechaYhora(year,month,dayOfMonth,hourOfDay);
	}


	public Date getFechaYhora() {
		return fechaYhora;
	}


	public void setFechaYhora(Date fechaYhora) {
		this.fechaYhora = fechaYhora;
	}

}
