package events;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AltaCuentaRequestEvent {
	private BigInteger nroTarjeta, nroCuenta;
	private BigDecimal limiteDesc;
	private String tipoCuenta;

	public AltaCuentaRequestEvent(BigInteger nroTarjeta, BigInteger nroCuenta, BigDecimal limiteDesc, String tipoCuenta) {
		this.setNroTarjeta(nroTarjeta);
		this.setNroCuenta(nroCuenta);
		this.setLimiteDesc(limiteDesc);
		this.setTipoCuenta(tipoCuenta);
		
	}

	public BigInteger getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(BigInteger nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public BigInteger getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(BigInteger nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public BigDecimal getLimiteDesc() {
		return limiteDesc;
	}

	public void setLimiteDesc(BigDecimal limiteDesc) {
		this.limiteDesc = limiteDesc;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}
