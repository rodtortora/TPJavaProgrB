package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Banco {
	private Collection<Usuario> usuarios = new ArrayList<>();
	private Collection<TarjetaATM> tarjetas = new ArrayList<>();
	private TarjetaATM tarjeta;
	private String nombre;
	private BigInteger minRango, maxRango;
	
	public Banco() {};
	
	public Banco(String nombre, BigInteger minRango, BigInteger maxRango) {
		this.nombre = nombre;
		this.minRango = minRango;
		this.maxRango = maxRango;
	}
	
	public TarjetaATM getTarjetaEvaluada() {
		return tarjeta;
	}
	
	public void setTarjetaEvaluada(TarjetaATM tarjeta) {
		this.tarjeta = tarjeta;
	}
	

	public boolean validarTarjeta(BigInteger idTarjetaATM, int PIN) {
		if (cardIsOnWhitelist(idTarjetaATM)) {
			return validarPIN(PIN);
		}
		return false;
	}	
	
	public boolean cardIsOnWhitelist(BigInteger idTarjetaATM) {
		if ((idTarjetaATM.compareTo(this.getMinRango()) == 0) || (idTarjetaATM.compareTo(this.getMinRango()) == 1)) {
			if ((idTarjetaATM.compareTo(this.getMaxRango()) == 0) || (idTarjetaATM.compareTo(this.getMaxRango()) == -1)) {
				Iterator<TarjetaATM> ittarjetas = tarjetas.iterator();
				while (ittarjetas.hasNext() && idTarjetaATM.compareTo(getTarjetaEvaluada().getID()) != 0) {
					setTarjetaEvaluada(ittarjetas.next());
				}
				if (idTarjetaATM.compareTo(getTarjetaEvaluada().getID()) == 0) {
					if (!getTarjetaEvaluada().isHabilitada()) {
						return false;
					}
					return true;
				} else {
					System.out.println("Error en busqueda de tarjeta"); //TODO: handle error
					return false;
				}
			}
		}
		return false;
		
	}
	
	public boolean validarPIN(int PIN) {
		while (PIN != getTarjetaEvaluada().getPIN() && getTarjetaEvaluada().getIntentosFallidos() < 3) {
			//TODO: genera un evento del tipo PinFailedEvent, solicita ingreso de PIN nuevamente a la vista
			getTarjetaEvaluada().setIntentosFallidos();
		}
		if (PIN == tarjeta.getPIN()) {
			getTarjetaEvaluada().setIntentosFallidos(0);
			return true;
		}
		if (getTarjetaEvaluada().getIntentosFallidos() == 3) {
			getTarjetaEvaluada().setHabilitada(false);
			System.out.println("Su tarjeta fue inhabilitada y retenida por el banco"); //TODO: menu tarjeta inhabilitada
		}
		return false;
			
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
