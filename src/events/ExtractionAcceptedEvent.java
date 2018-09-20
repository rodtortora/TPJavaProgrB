package events;

import java.math.BigDecimal;

public class ExtractionAcceptedEvent {
	
	private BigDecimal cantidadExtraida, SaldoRestante;

	public ExtractionAcceptedEvent(BigDecimal cantidadExtraida, BigDecimal saldoRestante) {
		this.setCantidadExtraida(cantidadExtraida);
		this.setSaldoRestante(saldoRestante);

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

}
