package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Usuario {
	private Cuenta cuenta;
	private BigInteger nroTarjeta;
	private String apellido, nombre;
	private ArrayList<Cuenta> cuentas = new ArrayList<>();
	
	public Usuario(BigInteger nroTarjeta, String apellido, String nombre) {
		this.nroTarjeta = nroTarjeta;
		this.apellido = apellido;
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCuenta(ArrayList<Cuenta> cuenta) {
		this.cuentas = cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuentas.add(cuenta);
	}
	
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	
	public Cuenta buscarCuenta(BigInteger cbu) {
		Iterator<Cuenta> itCuentas = cuentas.iterator();
		Cuenta c = null;
		boolean cuentaEncontrada = false;
		while (itCuentas.hasNext() && cuentaEncontrada == false) {
			c = itCuentas.next();
			if (c.getCBU().compareTo(cbu) == 0) {
				cuentaEncontrada = true;
			}
		}
		if (cuentaEncontrada == true) {
			return c;
		}
		return null;
	}
	
	public Cuenta getCuentas(Cuenta c) {
		Iterator<Cuenta> itcuentas = cuentas.iterator();
		while (itcuentas.hasNext()) {
			Cuenta cuenta = itcuentas.next();
			if (c.equals(cuenta)) {
				return cuenta;
			}
		}
		return null;
	}
	public BigInteger getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(BigInteger nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
}
