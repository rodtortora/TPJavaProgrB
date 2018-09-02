package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import events.CardValidatedEvent;
import events.CardValidatedListener;
import events.PinRequestEvent;
import events.PinRequestListener;
import events.PinSentListener;
import exceptions.BlockCardException;
import exceptions.WrongPinException;

public class Banco {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<TarjetaATM> tarjetas = new ArrayList<>();
	private int ID;
	private TarjetaATM tarjeta;
	private String nombre;
	private BigInteger minRango, maxRango;
	private PinRequestListener pinRequestListener;
	private CardValidatedListener cardValidatedListener;
	
	public Banco() {};
	
	/**
	 * Constructor
	 * @param ID
	 * @param nombre
	 * @param minRango
	 * @param maxRango
	 */
	
	public Banco(int ID, String nombre, BigInteger minRango, BigInteger maxRango) {
		this.ID = ID;
		this.nombre = nombre;
		this.minRango = minRango;
		this.maxRango = maxRango;
	}
	
	/**
	 * Getters & Setters
	 */
	
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
	public void setTarjetas(TarjetaATM tarjeta) { //TODO
		if ((tarjeta.getID().compareTo(this.getMinRango()) >= 0) && tarjeta.getID().compareTo(this.getMaxRango()) <= 0) {
			this.tarjetas.add(tarjeta);
			System.out.println("Tarjeta agregada con exito");
		} else {
			System.out.println("Error en banco.setTarjetas");
		}
	}
	
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
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
	
	public CardValidatedListener getCardValidatedListener() {
		return cardValidatedListener;
	}

	public void setCardValidatedListener(CardValidatedListener cardValidatedListener) {
		this.cardValidatedListener = cardValidatedListener;
	}

	/**
	 * Se valida que la tarjeta consultada al banco existe en la whitelist del banco.
	 * 
	 * @param idTarjetaATM número de tarjeta enviado
	 * @return Si la validación se hace correctamente, se devuelve la tarjeta con sus datos. Caso contrario devuelve null.
	 */
	
	public TarjetaATM cardIsOnWhitelist(BigInteger idTarjetaATM) {
		setTarjetaEvaluada(null);
		if ((idTarjetaATM.compareTo(this.getMinRango()) >= 0)) {
			if ((idTarjetaATM.compareTo(this.getMaxRango()) <= 0)) {
				Iterator<TarjetaATM> ittarjetas = tarjetas.iterator();
				TarjetaATM cardIterated;
				while (ittarjetas.hasNext() && this.getTarjetaEvaluada() == null) {					
					cardIterated = ittarjetas.next();
					if (idTarjetaATM.compareTo(cardIterated.getID()) == 0) {
						setTarjetaEvaluada(cardIterated);
					}
				}
				if (getTarjetaEvaluada() != null) {
					return getTarjetaEvaluada();
				}
			}
		}
		return null;		
	}
	
	/**
	 * Se valida si la tarjeta tiene permitido operar en el banco, y solicita el PIN.
	 * En el caso de que la tarjeta esté inactiva, reportará el error correspondiente.
	 * @param tarjetaATM 
	 */

	public void validarTarjeta(TarjetaATM tarjetaATM) {
		if (!tarjetaATM.isHabilitada()) {
			//TODO: Comunicar al ATM que la tarjeta no está habilitada para operar
		} else {
			pinRequestListener.listenPinRequestEvent(new PinRequestEvent());			
		}

	}
	
	/**
	 * Se valida que el PIN introducido sea válido para la tarjeta
	 * @param pin
	 */
	
	public void validarPIN(TarjetaATM tarjetaATM, String pin) throws WrongPinException, BlockCardException {
		if (!tarjetaATM.getPIN().equals(pin) && tarjetaATM.getIntentosFallidos() < 2) {	
			tarjetaATM.setIntentosFallidos(); //intentosfallidos++
			throw new WrongPinException();
		}
		if (tarjetaATM.getPIN().equals(pin) && tarjetaATM.isHabilitada()) {
			tarjetaATM.setIntentosFallidos(0);	
			this.getCardValidatedListener().listenCardValidatedEvent(new CardValidatedEvent(tarjetaATM));
		}
		if (tarjetaATM.getIntentosFallidos() == 2) {
			tarjetaATM.setHabilitada(false);
			throw new BlockCardException("Su tarjeta fue bloqueda por exceso de intentos de login fallidos");
		}	
	}
	

	


	@Override
	public String toString() {
		return "Banco " + getNombre();
	}
}
