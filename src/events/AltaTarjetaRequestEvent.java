package events;

import java.math.BigDecimal;
import java.math.BigInteger;

import model.Banco;

public class AltaTarjetaRequestEvent {
	
	private BigInteger nroTarjeta, nroCuenta;
	private BigDecimal limiteDesc;
	private Banco banco;
	private String nombre, apellido, cuit, pwd, tipoCuenta;

	public AltaTarjetaRequestEvent(BigInteger nroTarjeta, BigInteger nroCuenta, BigDecimal limiteDesc, String tipoCuenta, Banco banco, String nombre, String apellido, String cuit, String pwd) {
		this.setNroTarjeta(nroTarjeta);
		this.setNroCuenta(nroCuenta);
		this.setTipoCuenta(tipoCuenta);
		this.setLimiteDesc(limiteDesc);
		this.setBanco(banco);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setCuit(cuit);
		this.setPwd(pwd);
	}
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public BigInteger getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(BigInteger nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public BigInteger getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(BigInteger nroCuenta) {
		this.nroCuenta = nroCuenta;
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
