package model;

import java.util.Calendar;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import events.CardValidatedEvent;
import events.CardValidatedListener;
import events.PinRequestEvent;
import events.PinRequestListener;
import events.PinSentListener;
import exceptions.BlockCardException;
import exceptions.NotEnoughBalanceException;
import exceptions.WrongPinException;

public class Banco implements Serializable {
	private static final long serialVersionUID = -2071330853728301386L;
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

	public void validarTarjeta(TarjetaATM tarjetaATM) throws BlockCardException {
		if (!tarjetaATM.isHabilitada()) {
			throw new BlockCardException();
		} else {
			pinRequestListener.listenPinRequestEvent(new PinRequestEvent());			
		}

	}
	
	/**
	 * Se valida que el PIN introducido sea igual que el de la tarjeta
	 * @param pin
	 */
	
	private boolean validarPIN(TarjetaATM tarjetaATM, String pin) {
		if (tarjetaATM.getPIN().equals(pin)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Valida que la tarjeta y el PIN sean correctos para logearse	 
	 */

	public void login(TarjetaATM tarjetaATM, String pin) throws WrongPinException, BlockCardException {
		if (validarPIN(tarjetaATM,pin) && tarjetaATM.getIntentosFallidos() < 2) {
			tarjetaATM.setIntentosFallidos(0);
			this.getCardValidatedListener().listenCardValidatedEvent(new CardValidatedEvent(tarjetaATM));
		} else {
			if (tarjetaATM.getIntentosFallidos() < 2) {
				tarjetaATM.setIntentosFallidos(); // Intentos fallidos ++
				throw new WrongPinException("PIN incorrecto");
			} else {
				tarjetaATM.setHabilitada(false);
				throw new BlockCardException("Su tarjeta fue bloqueda por exceso de introduccion de PINs fallidos");
			}
		}
	}
	
	/**
	 * Valida que la tarjeta y el PIN sean correctos para cambiar el PIN	 
	 */

	public void changePIN(TarjetaATM tarjeta, String pinActual, String newPin) throws WrongPinException, BlockCardException {
		if (validarPIN(tarjeta,pinActual) && tarjeta.getIntentosFallidos() < 2) {
			tarjeta.setIntentosFallidos(0);
			tarjeta.setPIN(newPin);
		} else {
			if (tarjeta.getIntentosFallidos() < 2) {
				tarjeta.setIntentosFallidos(); // Intentos fallidos ++
				throw new WrongPinException("PIN incorrecto");
			} else {
				tarjeta.setHabilitada(false);
				throw new BlockCardException("Su tarjeta fue bloqueda por exceso de introduccion de PINs fallidos");
			}
		}
	}

	public BigDecimal obtenerSaldo(TarjetaATM tarjeta, Cuenta cuenta) {
		return tarjeta.getUsuario().getCuentas(cuenta).getSaldo();
	}

	@Override
	public String toString() {
		return "Banco " + getNombre();
	}

	public void extraer(BigDecimal cantidadExtraer, ArrayList<Tarifa> tarifasTransaccion, Cuenta cuenta) throws NotEnoughBalanceException {
		BigDecimal impuestoTransaccion = BigDecimal.ZERO;
		if (tarifasTransaccion != null) {
			for(Tarifa tarifa : tarifasTransaccion) {
				impuestoTransaccion.add(tarifa.getValor());
			}	
		}
		if (cuenta.getSaldo().add(cuenta.getLimiteDescubierto()).subtract(impuestoTransaccion).compareTo(cantidadExtraer) >= 0) { /** El saldo junto 
		con el limite descubierto es mayor a la cantidad a extraer */
			cuenta.setSaldo(cuenta.getSaldo().subtract(cantidadExtraer.add(impuestoTransaccion)));
			Calendar fecha = new GregorianCalendar();
			cuenta.addTransaction(new Transaction(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND,
					tarifasTransaccion, cantidadExtraer));
			
		} else {
			throw new NotEnoughBalanceException();
		}

		
	}




}
