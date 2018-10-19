package model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Transferencia extends Transaction {
	
	private Cuenta cuentaOrigen, cuentaDestino;

	public Transferencia(Calendar fechaTransaccion, BigDecimal moneyAmount, int tipoTransaccion, boolean esDebito, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		super(fechaTransaccion, moneyAmount, tipoTransaccion, esDebito);
		this.setCuentaOrigen(cuentaOrigen);
		this.setCuentaDestino(cuentaDestino);
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

}
