package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

public class Banco {
	private Collection<Usuario> usuarios = new ArrayList<>();
	private Collection<TarjetaATM> tarjetas = new ArrayList<>();
	private String nombre;
	private BigInteger minRango, maxRango;
	
	public Banco() {};
	
	public Banco(String nombre, BigInteger minRango, BigInteger maxRango) {
		this.nombre = nombre;
		this.minRango = minRango;
		this.maxRango = maxRango;
	}	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigInteger getMinRango() {
		return minRango;
	}
	public void setMinRango(BigInteger minRango) {
		this.minRango = minRango;
	}
	public BigInteger getMaxRango() {
		return maxRango;
	}
	public void setMaxRango(BigInteger maxRango) {
		this.maxRango = maxRango;
	}
	public Collection<TarjetaATM> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(Collection<TarjetaATM> tarjetas) {
		this.tarjetas = tarjetas;
	}
	public void addTarjeta(TarjetaATM tarjeta) {
		if ((tarjeta.getID().compareTo(this.getMinRango()) == 0) || (tarjeta.getID().compareTo(this.getMinRango()) == 1)) {
			if ((tarjeta.getID().compareTo(this.getMaxRango()) == 0) || (tarjeta.getID().compareTo(this.getMaxRango()) == -1)) {
				this.tarjetas.add(tarjeta);
				System.out.println("Tarjeta agregada con exito");
			} else {
				System.out.println("Error en banco.setTarjetas");
			}
		}
		else {
			System.out.println("Error en banco.setTarjetas");
		}
	}
	
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	@Override
	public String toString() {
		return "Banco " + getNombre();
	}
}
