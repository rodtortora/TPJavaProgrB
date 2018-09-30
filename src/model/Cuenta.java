package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public abstract class Cuenta {
	private BigInteger CBU;
	private BigDecimal saldo, mantenimientoMensual, limiteExtraccionDiario, limiteDescubierto;
	private int limiteExtraccionesMensualSinCargo; // Cantidad extracciones sin cargo por mes
	private ArrayList<Transaction> transacciones = new ArrayList<>();
	private String tipoCuenta;

	/**
	 * Constructor
	 * @param CBU: nro de cuenta
	 * @param mantenimientoMensual
	 * @param saldo
	 * @param tasaExtraccOtroBanco: 
	 * @param limiteExtraccionesSC
	 * @param tasaExtraccion
	 * @param limiteExtraccionDiario
	 * @param limiteDescubierto
	 * @param tipoCta
	 */
	
	public Cuenta(BigInteger CBU, BigDecimal mantenimientoMensual, BigDecimal saldo, BigDecimal limiteExtraccionDiario, BigDecimal limiteDescubierto, String tipoCta, int limiteExtraccionesSC) {
		this.setCBU(CBU);
		this.setSaldo(saldo);
		this.setMantenimientoMensual(mantenimientoMensual);
		this.setLimiteExtraccionDiario(limiteExtraccionDiario);
		this.setLimiteDescubierto(limiteDescubierto);
		this.setTipoCuenta(tipoCta);
		this.setLimiteExtraccionesMensualSinCargo(limiteExtraccionesSC);

	}
	
	/**
	 * Getters & Setters
	 */
	
	public BigInteger getCBU() {
		return CBU;
	}

	public void setCBU(BigInteger CBU) {
		this.CBU = CBU;
	}
	
	public ArrayList<Transaction> getTransacciones() {
		return transacciones;
	}

	public BigDecimal getSaldo() { // Saldo total
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getMantenimientoMensual() {
		return mantenimientoMensual;
	}

	public void setMantenimientoMensual(BigDecimal mantenimientoMensual) {
		this.mantenimientoMensual = mantenimientoMensual;
	}

	public int getLimiteExtraccionesSinCargoMensual() {
		return limiteExtraccionesMensualSinCargo;
	}

	public void setLimiteExtraccionesMensualSinCargo(int limiteExtraccionesSC) {
		this.limiteExtraccionesMensualSinCargo = limiteExtraccionesSC;
	}

	public BigDecimal getLimiteExtraccionDiario() {
		return limiteExtraccionDiario;
	}

	public void setLimiteExtraccionDiario(BigDecimal limiteExtraccion) {
		this.limiteExtraccionDiario = limiteExtraccion;
	}

	public BigDecimal getLimiteDescubierto() {
		return limiteDescubierto;
	}

	public void setLimiteDescubierto(BigDecimal limiteDescubierto) {
		this.limiteDescubierto = limiteDescubierto;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCta) {
		this.tipoCuenta = tipoCta;
	}
	
	
	/**
	 * Functions
	 */
	
	public int getCantTransaccionesUltMes() {
		int count = 0;
		Calendar fechaActual = Calendar.getInstance();
		Iterator<Transaction> itTransacciones = this.getTransacciones().iterator();		
		while (itTransacciones.hasNext()) {
			Transaction transaction = itTransacciones.next();
			if (transaction.getFechaTransaccion().get(Calendar.MONTH) == fechaActual.get(Calendar.MONTH)) {
				count++;
			}
		}
		return count;
	}
	
	public int getCantTransaccionesUltMes(int tipoTransaccion) {
		int count = 0;
		Calendar fechaActual = Calendar.getInstance();
		Iterator<Transaction> itTransacciones = this.getTransacciones().iterator();		
		while (itTransacciones.hasNext()) {
			Transaction transaction = itTransacciones.next();
			if (transaction.getFechaTransaccion().get(Calendar.MONTH) == fechaActual.get(Calendar.MONTH) && transaction.getTipoTransaccion() == tipoTransaccion) {
				count++;
			}
		}
		return count;
	}
	
	
	public void addTransaction(Transaction transaction) {
		this.transacciones.add(transaction);
	}
	
	@Override
	public String toString() {
		return this.CBU.toString() + " - " + this.getTipoCuenta();
	}




	
	
}
