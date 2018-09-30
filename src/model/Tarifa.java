package model;

import java.math.BigDecimal;

public class Tarifa {
	
	public final static BigDecimal cajaAhorroTransaccionForanea = BigDecimal.valueOf(10);
	public final static BigDecimal cuentaCorrienteTransaccionForanea =  BigDecimal.valueOf(30);
	public final static BigDecimal extraccion =  BigDecimal.valueOf(15);
	
	private int tipoTransaccion;
	private BigDecimal valor;
	
	public Tarifa(BigDecimal valor, int tipoTransaccion) {
		this.setValor(valor);
		this.setTipoTransaccion(tipoTransaccion);
	}

	public int getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(int tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
