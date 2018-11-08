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
import events.MovementAcceptedEvent;
import events.MovementsReturnedEvent;
import events.MovementsReturnedListener;
import events.ExtractionAcceptedListener;
import events.PinRequestEvent;
import events.PinRequestListener;
import events.PinSentListener;
import exceptions.AccountNotFoundException;
import exceptions.BlockCardException;
import exceptions.ExtractionLimitExceeded;
import exceptions.NotAllowedOperation;
import exceptions.NotEnoughBalanceException;
import exceptions.WrongPinException;

public class Banco implements Serializable {
	private static final long serialVersionUID = -2071330853728301386L;
	private List<Cuenta> cuentas = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	private List<TarjetaATM> tarjetas = new ArrayList<>();
	private int ID;
	private TarjetaATM tarjeta;
	private String nombre;
	private BigInteger minRango, maxRango;
	private PinRequestListener pinRequestListener;
	private CardValidatedListener cardValidatedListener;
	private ExtractionAcceptedListener movementAcceptedListener;
	private boolean permiteMostrarMovimientos;
	private MovementsReturnedListener movementsReturnedListener;

	
	public Banco() {};
	
	/**
	 * Constructor
	 * @param ID
	 * @param nombre
	 * @param minRango
	 * @param maxRango
	 */
	
	public Banco(int ID, String nombre, BigInteger minRango, BigInteger maxRango, List<Cuenta> cuentas, boolean permiteMostrarMovimientos) {
		this.ID = ID;
		this.nombre = nombre;
		this.minRango = minRango;
		this.maxRango = maxRango;
		this.cuentas = cuentas;
		this.setPermiteMostrarMovimientos(permiteMostrarMovimientos);
	}
	
	/**
	 * Getters & Setters
	 */
	
	public void setExtractionAcceptedListener(ExtractionAcceptedListener extractionAcceptedListener) {
		this.movementAcceptedListener = extractionAcceptedListener;
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
	
	public void setMovementsReturnedListener(MovementsReturnedListener listener) {
		this.movementsReturnedListener = listener;
	}

	/**
	 * Se valida que la tarjeta consultada al banco existe en la whitelist del banco.
	 * 
	 * @param idTarjetaATM número de tarjeta enviado
	 * @return Si la validación se hace correctamente, se devuelve la tarjeta con sus datos. Caso contrario devuelve null.
	 */
	
	public boolean cardIsOnRange(BigInteger idTarjetaATM) {
		if ((idTarjetaATM.compareTo(this.getMinRango()) >= 0)) {
			if ((idTarjetaATM.compareTo(this.getMaxRango()) <= 0)) {
				return true;
			}
		}
		return false;
	}
	
	public TarjetaATM cardIsOnWhitelist(BigInteger idTarjetaATM) {
		setTarjetaEvaluada(null);
		if (cardIsOnRange(idTarjetaATM)) {
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

	public void extraer(BigDecimal moneyAmount, ArrayList<Tarifa> tarifasTransaccion, Cuenta cuenta, BigDecimal impuestoTransaccion) throws NotEnoughBalanceException, ExtractionLimitExceeded {
		
		if (moneyAmount.compareTo(cuenta.getLimiteExtraccionDiario()) > 0) {
			throw new ExtractionLimitExceeded("Supero el limite de extraccion diario");
		}
		
		if (cuenta.getSaldo().add(cuenta.getLimiteDescubierto()).subtract(impuestoTransaccion).compareTo(moneyAmount) >= 0) { 
			/** El saldo junto con el limite descubierto disponible, es suficiente para la cantidad a extraer y pagar las tarifas */
			cuenta.setSaldo(cuenta.getSaldo().subtract(moneyAmount.add(impuestoTransaccion)));
			Calendar fechaTransaccion = Calendar.getInstance();		  
			cuenta.addTransaction(new Transaction(fechaTransaccion, moneyAmount, TipoTransaccion.extraccion, true));
			if (!tarifasTransaccion.isEmpty()) {
				for(Tarifa tarifa : tarifasTransaccion) {
					if (tarifa != null) {
						cuenta.addTransaction(new Transaction(fechaTransaccion,tarifa.getValor(),tarifa.getTipoTransaccion(), true));
					}				
				}	
			}
			cuenta.setLimiteExtraccionDiario(cuenta.getLimiteExtraccionDiario().subtract(moneyAmount));
			this.movementAcceptedListener.listenMovementAcceptedEvent(new MovementAcceptedEvent(TipoTransaccion.extraccion, moneyAmount, cuenta.getSaldo()));
			
			
		} else {
			throw new NotEnoughBalanceException("Saldo insuficiente");
		}

		
	}

	public void depositar(BigDecimal moneyAmount, ArrayList<Tarifa> tarifasTransaccion, Cuenta cuenta, BigDecimal impuestoTransaccion) {
		cuenta.setSaldo(cuenta.getSaldo().add(moneyAmount.subtract(impuestoTransaccion)));
		Calendar fechaTransaccion = Calendar.getInstance();	
		cuenta.addTransaction(new Transaction(fechaTransaccion, moneyAmount, TipoTransaccion.depositoEfectivo, false));
		if (!tarifasTransaccion.isEmpty()) {
			for(Tarifa tarifa : tarifasTransaccion) {
				if (tarifa != null) {
					cuenta.addTransaction(new Transaction(fechaTransaccion,tarifa.getValor(),tarifa.getTipoTransaccion(), true));
				}				
			}	
		}
		this.movementAcceptedListener.listenMovementAcceptedEvent(new MovementAcceptedEvent(TipoTransaccion.depositoEfectivo, moneyAmount, cuenta.getSaldo()));
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

	public void transferirDinero(BigDecimal moneyAmount, Cuenta cuentaOrigen, BigInteger nroCbuDestino) throws NotEnoughBalanceException, AccountNotFoundException {
		Cuenta cuentaDestino;
		cuentaDestino = buscarCuenta(nroCbuDestino);
		if (cuentaDestino != null) {
			if (cuentaOrigen.getSaldo().add(cuentaOrigen.getLimiteDescubierto()).compareTo(moneyAmount) >= 0) { 
				/** El saldo junto con el limite descubierto disponible, es suficiente para la cantidad a transferir */
				cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(moneyAmount));
				Calendar fechaTransaccion = Calendar.getInstance();		  
				cuentaOrigen.addTransaction(new Transferencia(fechaTransaccion, moneyAmount, TipoTransaccion.transferenciaEnviar, true, cuentaOrigen, cuentaDestino));
				cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(moneyAmount));
				cuentaDestino.addTransaction(new Transferencia(fechaTransaccion, moneyAmount, TipoTransaccion.transferenciaRecibir, false, cuentaOrigen, cuentaDestino));
				this.movementAcceptedListener.listenMovementAcceptedEvent(new MovementAcceptedEvent(TipoTransaccion.transferenciaEnviar, moneyAmount, cuentaOrigen.getSaldo()));	
			} else {
				throw new NotEnoughBalanceException("Saldo insuficiente");
			}			
		} else {
			throw new AccountNotFoundException("Cuenta destino no encontrada");
		}
				
	}
	

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	public void setCuentas(Cuenta cuenta) {
		this.cuentas.add(cuenta);
	}

	public void consultarMovimientos(int ano, int mes, Cuenta cuenta) throws NotAllowedOperation {
		ArrayList<Transaction> transacciones = new ArrayList<>();
		if (this.permiteMostrarMovimientos()) {
			for (Transaction transaccion : cuenta.getTransacciones()) {
				if (transaccion.getFechaTransaccion().get(Calendar.MONTH) == mes && transaccion.getFechaTransaccion().get(Calendar.YEAR) == ano) {
					transacciones.add(transaccion);
				}
			}
			movementsReturnedListener.listenMovementsReturnedEvent(new MovementsReturnedEvent(transacciones));
		} else {
			throw new NotAllowedOperation();
		}				
	}

	public boolean permiteMostrarMovimientos() {
		return permiteMostrarMovimientos;
	}

	public void setPermiteMostrarMovimientos(boolean permiteMostrarMovimientos) {
		this.permiteMostrarMovimientos = permiteMostrarMovimientos;
	}
}
