package model;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import exceptions.ATMisOnMaintenanceException;
import exceptions.BlockCardException;
import exceptions.CardNotFoundException;
import exceptions.InvalidNewPinException;
import exceptions.NotEnoughBalanceException;
import exceptions.WrongPinException;

public class ATM {
	private LectorTarjeta lector = new LectorTarjeta();
	private ArrayList<Banco> bancos = new ArrayList<>();
	private ArrayList<Billetero> billeteros = new ArrayList<>();
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

	public void addBilletero(Billetero billetero) {
		billeteros.add(billetero);
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
	
	public BigDecimal getLimiteExtraccionCajero() {
		
	}
	
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
	
	
	public void pedidoExtraccion(BigDecimal cantidad) throws NotEnoughBalanceException {
		ArrayList<Tarifa> tarifasTransaccion = new ArrayList<>();
		
		if (this.getLimiteExtracCajero() >= cantidad) {
			if (this.getCuentaSeleccionada().getTipoCuenta() == 1) { /** 1 = CAJA AHORRO, 2 = CUENTA SUELDO, 3 = CUENTA CORRIENTE */
				if (!this.isBancoATMIgualBancoTarjeta()) {
					tarifasTransaccion.add(this.getTarifas().get("CajaAhorroExtraccionForanea"));
				}
			} else {
				if (this.getCuentaSeleccionada().getTipoCuenta() == 3) {
					if (!this.isBancoATMIgualBancoTarjeta()) {
						tarifasTransaccion.add(this.getTarifas().get("CuentaCorrienteExtraccionForanea"));			
					}	
				}
			}
			if (this.getCuentaSeleccionada().getCantTransaccionesMes() >= this.getCuentaSeleccionada().getLimiteExtraccionesSinCargo()) {
				tarifasTransaccion.add(this.getTarifas().get("ImpuestoExtraccion"));
			}	
			
			this.getBancoActual().extraer(cantidad,tarifasTransaccion,this.getCuentaSeleccionada());
			
		}
	}
		
	
	public void extraerDinero(BigDecimal cantidad) {
		
	}
	
/*	public void calcularLimiteExtraccionCuenta(Cuenta cuenta) { //Calcula limite de extraccion de la cuenta seleccionada.
		if (this.isBancoATMIgualBancoTarjeta()) {
			this.limiteExtracCuentaSeleccionada.add(cuenta.getSaldo().add(cuenta.getLimiteDescubierto()));
		} if (cuenta.getLimiteExtraccionesSinCargo() <= cuenta.getExtraccionesRealizadas()) {
			this.limiteExtracCuentaSeleccionada.subtract(cuenta.getTasaExtraccion())
		}
		
	}*/
	
	/*public void extraer(BigDecimal valor) {
		if (this.getBancoActual() == null || lector.getTarjetaLeida() == null || this.getCuentaSeleccionada() == null) {
			System.out.println("ATM: Error: tarjeta, banco o cuenta son null");
		} else {
			if (this.getCuentaSeleccionada().this.getLimiteExtraccionCuenta().compareTo(valor) == -1) {
				System.out.println("ATM: No se puede extraer tanto dinero, supera saldo disponible de la cuenta. Reintente con cantidad menor o en otro momento");
			} else {						
					// Validar si se pueden combinar billetes para extraer la cantidad pedida
				}
			}
			// CC: X$ en descubierto, 30$ comision si saca de un banco que no es el suyo, 15$ a partir de 4ta extrac. mismo ATM. 300$ mensual
			// caja de ahorro: no descubierto, 10$ de comision por banco distinto, 150$ mant mensual
			// cuenta sueldo: caja de ahorro sin comisiones ni mantenimiento, guarda CUIT de la empresa que deposita sueldo
			// los valores de los cargos se obtienen de una lista de tarifas
	}*/
	
	@Override
	public String toString() {
		return this.ubicacion;
	}

}
