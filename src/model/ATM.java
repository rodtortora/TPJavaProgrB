package model;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import exceptions.ATMisOnMaintenanceException;
import exceptions.BlockCardException;
import exceptions.CardNotFoundException;
import exceptions.ExtractionLimitExceeded;
import exceptions.InvalidNewPinException;
import exceptions.NotEnoughBalanceException;
import exceptions.WrongPinException;

public class ATM {
	private LectorTarjeta lector = new LectorTarjeta();
	private ArrayList<Banco> bancos = new ArrayList<>();
	private SortedMap<BigInteger, Billetero> billeteros = new TreeMap(java.util.Collections.reverseOrder());
	private HashMap<String, Tarifa> tarifas = new HashMap <String, Tarifa> ();
	private int ID;
	private String ubicacion;
	private boolean modoMantenimiento;
	private Banco bancoATM; // Empresa bancaria a la cual pertenece el ATM. Influye en las tarifas de extraccion.
	private Banco bancoActual; // Banco al que pertenece la tarjeta leida
	private boolean bancoATMIgualBancoTarjeta;
	private Cuenta cuentaSeleccionada; // Cuenta seleccionada de la tarjeta leida
	private TarjetaATM tarjetaActual;
	
	/**
	 * Constructor
	 * @param ID
	 * @param ubicacion
	 * @param bancoATM
	 */
	
	public ATM(int ID, String ubicacion, Banco bancoATM, ArrayList<Banco> bancos) {
		this.ID = ID;
		this.ubicacion = ubicacion;
		this.bancoATM = bancoATM;
		this.setBancos(bancos);
	}
	

	/**
	 * Getters & Setters
	 */
	
	public ArrayList<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(ArrayList<Banco> bancos) {
		this.bancos = bancos;
	}
	
	public void setBancos(Banco banco) {
		this.bancos.add(banco);
	}
	
	public HashMap<String, Tarifa> getTarifas() {
		return tarifas;
	}


	public void setTarifas(HashMap<String, Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public TarjetaATM getTarjetaActual() {
		return tarjetaActual;
	}
	public void setTarjetaActual(TarjetaATM tarjetaActual) {
		this.tarjetaActual = tarjetaActual;
	}
	public int getID() {
		return ID;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Cuenta getCuentaSeleccionada() {
		return this.cuentaSeleccionada;
	}	
	public boolean isModoMantenimiento() {
		return modoMantenimiento;
	}
	public void setModoMantenimiento(boolean modoMantenimiento) {
		this.modoMantenimiento = modoMantenimiento;
	}
	public Banco getBancoActual() {
		return bancoActual;
	}
	public void setBancoActual(Banco bancoActual) {
		this.bancoActual = bancoActual;
	}
	public boolean isBancoATMIgualBancoTarjeta() {
		return bancoATMIgualBancoTarjeta;
	}
	public void setBancoATMIgualBancoTarjeta(boolean bancoATMIgualBancoTarjeta) {
		this.bancoATMIgualBancoTarjeta = bancoATMIgualBancoTarjeta;
	}
	public Banco getBancoATM() {
		return bancoATM;
	}
	public void setBancoATM(Banco bancoATM) {
		this.bancoATM = bancoATM;
	}
	public SortedMap<BigInteger, Billetero> getBilleteros() {
		return billeteros;
	}
	public void setBilleteros(SortedMap<BigInteger, Billetero> billeteros) {
		this.billeteros = billeteros;
	}
	
	/**
	 * Functions
	 */
	
	/**
	 * Valida el nro de tarjeta introducido en el lector. La unica responsabilidad del ATM es
	 * encontrar el banco al cual pertenece la tarjeta. El método cardIsOnWhitelist() retorna la cuenta del cliente con sus datos.
	 * @param idTarjetaATM
	 * @throws CardNotFoundException 
	 * @throws ATMisOnMaintenanceException 
	 * @throws BlockCardException 
	 */
	
	
	public void validarTarjeta(BigInteger idTarjetaATM) throws CardNotFoundException, ATMisOnMaintenanceException, BlockCardException {
		this.setBancoActual(null);
		if (!this.isModoMantenimiento()) {			
			lector.setTarjetaLeida(idTarjetaATM);
			Iterator<Banco> itbancos = bancos.iterator();
			while (itbancos.hasNext() && this.getBancoActual() == null) {
				Banco banco = itbancos.next();
				if (banco.cardIsOnWhitelist(lector.getTarjetaLeida()) != null) {					
					this.setBancoActual(banco);	
					this.setTarjetaActual(banco.cardIsOnWhitelist(lector.getTarjetaLeida()));
				} 
			}
			if (this.getBancoActual() != null && this.getTarjetaActual() != null) {
				this.getBancoActual().validarTarjeta(this.getTarjetaActual());	
				this.setBancoATMIgualBancoTarjeta(this.getBancoActual().getID() == this.getBancoATM().getID());								
			} else {
				lector.expulsarTarjeta();
				throw new CardNotFoundException("Tarjeta no encontrada");	
			}
			
		} else {
			lector.expulsarTarjeta();
			throw new ATMisOnMaintenanceException("ATM en mantenimiento, reintente mas tarde");
		}		
	}
	
	/**
	 * Envía un PIN al banco perteneciente a la tarjeta.
	 * @param pin
	 * @throws WrongPinException 
	 * @throws BlockCardException 
	 */
	
			
	public void sendPin(String pin) throws WrongPinException, BlockCardException {
		this.getBancoActual().login(this.getTarjetaActual(), pin);
	}
	
	
	public void elegirCuenta(Cuenta cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}
	

	
	public void ChangePIN(String pinActual, String newPin, String confirmNewPin) throws InvalidNewPinException, WrongPinException, BlockCardException {
		if (newPin.equals(confirmNewPin)) {
			this.getBancoActual().changePIN(this.getTarjetaActual(), pinActual, newPin);
		} else {
			throw new InvalidNewPinException("El nuevo PIN es invalido");
		}
		
	}
	
	/**
	 * @return saldo total de la cuenta seleccionada.
	 */
	
	public BigDecimal consultaSaldo() {
		return this.getBancoActual().obtenerSaldo(this.getTarjetaActual(), this.getCuentaSeleccionada());
	}
	
	
	public void pedidoExtraccion(BigInteger moneyAmount) throws NotEnoughBalanceException, ExtractionLimitExceeded {
		ArrayList<Tarifa> tarifasTransaccion = new ArrayList<>();
		Collection c = billeteros.values();
		Iterator billeterositr = c.iterator();
		BigInteger aux = moneyAmount;
		while(billeterositr.hasNext() && !aux.equals(0)) {
			Billetero billetero = (Billetero) billeterositr.next();
			BigInteger[] resultado = aux.divideAndRemainder(billetero.getValorBillete());
			/**
			 * resultado[0] -> moneyAmount / valorBillete. Ejemplo 1400 / 500 = 2
			 * resultado[1] -> moneyAmount % valorBillete. Ejemplo 1400 % 500 = 400
			 */
			if (resultado[0].compareTo(billetero.getCantidadBilletes()) <= 0) {
				/**
				 * Hay billetes suficientes para realizar la operacion. Se reserva la cantidad de billetes 
				 * necesarias para la operacion actual, y se actualiza aux.
				 */
				billetero.setCantidadBilletesReservados(resultado[0]);
				aux = resultado[1];
			} else {	
				/**
				 * No hay billetes suficientes para realizar la operacion. Se reserva el total de billetes
				 * disponible y se actualiza aux.
				 */
				billetero.setCantidadBilletesReservados(billetero.getCantidadBilletes());
				aux = aux.subtract(billetero.getValorBillete().multiply(billetero.getCantidadBilletes()));
			}
		}
		if (aux.compareTo(BigInteger.valueOf(0)) == 0) { // Si se cumple es que el ATM no tiene inconvenientes de dinero para hacer la extracción
			/**
			 * Aplicación de tarifas
			 */
			if (this.getCuentaSeleccionada().getTipoCuenta() == "CAJA AHORRO") {
				if (!this.isBancoATMIgualBancoTarjeta()) {
					tarifasTransaccion.add(this.getTarifas().get("CajaAhorroExtraccionForanea"));
				}
			} else {
				if (this.getCuentaSeleccionada().getTipoCuenta() == "CUENTA CORRIENTE") {
					if (!this.isBancoATMIgualBancoTarjeta()) {
						tarifasTransaccion.add(this.getTarifas().get("CuentaCorrienteExtraccionForanea"));			
					}	
				}
			}
			if (this.getCuentaSeleccionada().getCantTransaccionesMes() >= this.getCuentaSeleccionada().getLimiteExtraccionesSinCargo()) {
				if (this.getCuentaSeleccionada().getLimiteExtraccionesSinCargo() != 0) {
					tarifasTransaccion.add(this.getTarifas().get("ImpuestoExtraccion"));
				}	
			}	
			
			/**
			 * Pedido de autorización al banco para hacer la extracción
			 */
			
			this.getBancoActual().extraer(BigDecimal.valueOf(moneyAmount.floatValue()),tarifasTransaccion,this.getCuentaSeleccionada());
			
		} else {
			throw new NotEnoughBalanceException("No es posible extraer esta cantidad.");
		}
	}
	
	public void expulsarDineroReservado() {
		BigInteger sumatoria = BigInteger.valueOf(0);
		Collection c = billeteros.values();
		Iterator billeterositr = c.iterator();
		while (billeterositr.hasNext()) {
			Billetero billetero = (Billetero) billeterositr.next();
			billetero.expulsarDineroReservado();
			sumatoria.add(billetero.getCantidadBilletesReservados().multiply(billetero.getValorBillete()));		
			billetero.setCantidadBilletesReservados(BigInteger.ZERO);
		}		
	}

	@Override
	public String toString() {
		return this.ubicacion;
	}




}
