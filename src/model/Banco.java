package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import events.PinFailedEvent;
import events.PinFailedListener;
import events.PinRequestEvent;
import events.PinRequestListener;
import events.PinSentListener;

public class Banco {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<TarjetaATM> tarjetas = new ArrayList<>();
	private TarjetaATM tarjeta;
	private String nombre;
	private BigInteger minRango, maxRango;
	private PinRequestListener pinRequestListener;
	private PinFailedListener pinFailedListener;
	
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

	public void setPinRequestListener(PinRequestListener pinRequestListener) {
		this.pinRequestListener = pinRequestListener;
	}

	public void setPinFailedListener(PinFailedListener pinFailedListener) {
		this.pinFailedListener = pinFailedListener;
	}
	
	/**
	 * Se valida que la tarjeta consultada al banco existe en la whitelist del banco.
	 * Si la validación se hace correctamente, se setea el parámetro 'tarjetaEvaluada' 
	 * @param idTarjetaATM número de tarjeta enviado
	 * @return retorna true si se pudo validar que la tarjeta existe en el banco.
	 */
	
	public boolean cardIsOnWhitelist(BigInteger idTarjetaATM) {
		setTarjetaEvaluada(null);
		if ((idTarjetaATM.compareTo(this.getMinRango()) >= 0)) {
			if ((idTarjetaATM.compareTo(this.getMaxRango()) <= 0)) {
				Iterator<TarjetaATM> ittarjetas = tarjetas.iterator();
				TarjetaATM cardIterated;
				while (ittarjetas.hasNext() && getTarjetaEvaluada() == null) {					
					cardIterated = ittarjetas.next();
					if (idTarjetaATM.compareTo(cardIterated.getID()) == 0) {
						setTarjetaEvaluada(cardIterated);
					}
				}
				if (getTarjetaEvaluada() != null) {
					return true;
				}
			}
		}
		return false;		
	}
	
	/**
	 * Se valida si la tarjeta evaluada de la sesión actual, tiene permitido operar en el banco, y solicita el PIN.
	 * En el caso de que la tarjeta esté inactiva, reportará el error correspondiente.
	 */

	public void validarTarjeta() {
		if (getTarjetaEvaluada() != null) {
			if (!getTarjetaEvaluada().isHabilitada()) {
				//TODO: Comunicar al ATM que la tarjeta no está habilitada para operar
			} else {
				pinRequestListener.listenPinRequestEvent(new PinRequestEvent());			
			}
		}
	}
	
	/**
	 * Se valida que el PIN introducido sea válido para la tarjeta que está siendo evaluada (getTarjetaEvaluada())
	 * @param pin
	 */
	
	public void validarPIN(int pin) {
		if (pin != getTarjetaEvaluada().getPIN() && getTarjetaEvaluada().getIntentosFallidos() < 3) {			
			getTarjetaEvaluada().setIntentosFallidos(); //intentosfallidos++
			System.out.println("Banco-Debug: "+getTarjetaEvaluada().getIntentosFallidos());
			pinFailedListener.listenPinFailedEvent(new PinFailedEvent());
		}
		if (pin == tarjeta.getPIN() && tarjeta.isHabilitada()) {
			getTarjetaEvaluada().setIntentosFallidos(0);	
			System.out.println("Banco: tarjeta validada");
		}
		if (getTarjetaEvaluada().getIntentosFallidos() == 3) {
			getTarjetaEvaluada().setHabilitada(false);
			System.out.println("Banco: Su tarjeta fue inhabilitada y retenida por el banco"); //TODO: menu tarjeta inhabilitada	
		}	
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
	public void setTarjetas(List<TarjetaATM> tarjetas) {
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
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	@Override
	public String toString() {
		return "Banco " + getNombre();
	}
}
