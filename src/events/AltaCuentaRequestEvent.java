package events;

import java.math.BigDecimal;
import java.math.BigInteger;

import model.Banco;

public class AltaCuentaRequestEvent {
	private BigInteger nroTarjeta, nroCuenta;
	private BigDecimal limiteDesc;
	private String tipoCuenta, cuit;
	private Banco banco;

	public AltaCuentaRequestEvent(BigInteger nroTarjeta, BigInteger nroCuenta, BigDecimal limiteDesc, String tipoCuenta, Banco banco, String cuit) {
		this.setNroTarjeta(nroTarjeta);
		this.setNroCuenta(nroCuenta);
		this.setLimiteDesc(limiteDesc);
		this.setTipoCuenta(tipoCuenta);
		this.setBanco(banco);
		this.setCuit(cuit);
		
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

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
