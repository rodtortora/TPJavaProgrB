package events;

import java.math.BigDecimal;

public class MovementAcceptedEvent {
	
	private int tipoExtraccion;
	private BigDecimal cantidadExtraida, SaldoRestante;

	public MovementAcceptedEvent(int tipoExtraccion, BigDecimal cantidadExtraida, BigDecimal saldoRestante) {
		this.setCantidadExtraida(cantidadExtraida);
		this.setSaldoRestante(saldoRestante);
		this.setTipoExtraccion(tipoExtraccion);

	}

	public BigDecimal getCantidadExtraida() {
		return cantidadExtraida;
	}

	public void setCantidadExtraida(BigDecimal cantidadExtraida) {
		this.cantidadExtraida = cantidadExtraida;
	}

	public BigDecimal getSaldoRestante() {
		return SaldoRestante;
	}

	public void setSaldoRestante(BigDecimal saldoRestante) {
		SaldoRestante = saldoRestante;
	}

	public int getTipoExtraccion() {
		return tipoExtraccion;
	}

	public void setTipoExtraccion(int tipoExtraccion) {
		this.tipoExtraccion = tipoExtraccion;
	}

}
