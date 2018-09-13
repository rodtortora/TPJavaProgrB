package model;

import java.math.BigDecimal;

public class Tarifa {
	
	/**
	 * Tipos de tarifa:
	 * EXTRACCION BANCO FORANEO
	 * EXTRACCION
	 * DEPOSITO
	 * TRANSFERENCIA
	 * OTROS
	 */
	
	private BigDecimal valor;
	private String tipoTarifa;
	
	public Tarifa(String tipoTarifa, BigDecimal valor) {
		this.setValor(valor);
		this.setTipoTarifa(tipoTarifa);
	}

	public String getTipoTarifa() {
		return tipoTarifa;
	}

	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
