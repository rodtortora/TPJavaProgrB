package events;

import java.math.BigInteger;

import model.Banco;

public class ModificacionRequestEvent {
	
	private BigInteger nroTarjeta;
	private Banco banco;
	private String nombre, apellido, cuit, pwd;

	public ModificacionRequestEvent(BigInteger nroTarjeta,Banco banco,String nombre,String apellido,String cuit,String pwd) {
		this.setNroTarjeta(nroTarjeta);
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

}
