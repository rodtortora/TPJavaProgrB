package events;

import java.math.BigInteger;

public class DepositRequestEvent {
	private BigInteger valorBillete, cantidadBillete;

	public DepositRequestEvent(BigInteger valorBillete, BigInteger cantidadBillete) {
		this.setCantidadBillete(cantidadBillete);
		this.setValorBillete(valorBillete);
		
	}

	public BigInteger getValorBillete() {
		return valorBillete;
	}

	public void setValorBillete(BigInteger valorBillete) {
		this.valorBillete = valorBillete;
	}

	public BigInteger getCantidadBillete() {
		return cantidadBillete;
	}

	public void setCantidadBillete(BigInteger cantidadBillete) {
		this.cantidadBillete = cantidadBillete;
	}

}
