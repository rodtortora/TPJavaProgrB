package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Usuario {
	private int id;
	private Cuenta cuenta;

	private String apellido, nombre;
	private ArrayList<Cuenta> cuentas = new ArrayList<>();
	public Usuario(int id, String apellido, String nombre) {
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
