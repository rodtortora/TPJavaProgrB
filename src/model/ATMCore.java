package model;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import controller.AuthenticationController;

public class ATMCore {
	private LectorTarjeta lector = new LectorTarjeta();
	private ArrayList<Banco> bancos = new ArrayList<>();
	private ArrayList<Billetero> billeteros = new ArrayList<>();
	private int ID;
	private String ubicacion;
	private boolean modoMantenimiento;
	private Banco bancoATM; // Empresa bancaria a la cual pertenece el ATM. Influye en las tarifas de extraccion.
	private Banco bancoActual; // Banco al que pertenece la tarjeta leida
	private boolean bancoATMIgualBancoTarjeta;
	private Cuenta cuentaSeleccionada; // Cuenta seleccionada de la tarjeta leida
	private BigDecimal limiteExtracCuentaSeleccionada;
	
	public ATMCore(int ID, String ubicacion, Banco bancoATM) {
		this.ID = ID;
		this.ubicacion = ubicacion;
		this.bancoATM = bancoATM;
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
	public BigDecimal getLimiteExtracCuentaSeleccionada() {
		return limiteExtracCuentaSeleccionada;
	}
	public void setLimiteExtracCuentaSeleccionada(BigDecimal limiteExtracCuentaSeleccionada) {
		this.limiteExtracCuentaSeleccionada = limiteExtracCuentaSeleccionada;
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
	
	public void addBanco(Banco banco) {
		this.bancos.add(banco);
	}

	public void addBilletero(Billetero billetero) {
		billeteros.add(billetero);
	}
	
	// Primer metodo que se debe ejecutar
	public void validarTarjeta(BigInteger idTarjetaATM, int PIN) {
		if (!this.isModoMantenimiento()) {					
			lector.setTarjetaLeida(idTarjetaATM);
			Iterator<Banco> itbancos = bancos.iterator();
			while (itbancos.hasNext() && this.getBancoActual() == null) {
				Banco banco = itbancos.next();
				if (banco.validarTarjeta(lector.getTarjetaLeida(), PIN)) {
					this.setBancoActual(banco);
					this.setBancoATMIgualBancoTarjeta(this.getBancoActual() == this.getBancoATM());
					System.out.println(banco.toString());
					//TODO: mostrar menu correspondiente para consumir el resto de metodos
				} else {
					System.out.println("ATM: Error en validacion tarjeta"); //TODO: hand error
					if (banco.getTarjetaEvaluada().isHabilitada() == false) {
						lector.retenerTarjeta(idTarjetaATM);
						System.out.println("ATM: tarjeta retenida por el banco");
					} else {
						System.out.println("ATM: expulsando tarjeta");
						lector.expulsarTarjeta();
						
					}
					banco.setTarjetaEvaluada(null);
				}
			}
		}
	}
	
	public void elegirCuenta(Cuenta cuentaSeleccionada) {
		if (getBancoActual().getTarjetaEvaluada().getUsuario().getCuenta().size() > 1) {
			this.cuentaSeleccionada = cuentaSeleccionada;
		} else {
			System.out.println("ATM: No hay otra cuenta para elegir");
		}
	}
	
	public Cuenta getCuentaSeleccionada() {
		return this.cuentaSeleccionada;
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
	
	

}
