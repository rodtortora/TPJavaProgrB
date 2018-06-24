package tp;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Cuenta {
	private BigInteger CBU;
	private BigDecimal saldo;
	
	public Cuenta(BigInteger cBU, BigDecimal saldo) {
		super();
		CBU = cBU;
		this.saldo = saldo;
	}
	
	public abstract BigDecimal getSaldoDisponible(); // Saldo disponible para movimientos (tiene en cuenta límite de extraccion, tarifas y descubiertos)

	public BigInteger getCBU() {
		return CBU;
	}

	public void setCBU(BigInteger CBU) {
		this.CBU = CBU;
	}

	public BigDecimal getSaldo() { // Saldo total
		return saldo;
	}
	
	

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String tipoCta() {
		return this.toString();
	}
	
	
}
