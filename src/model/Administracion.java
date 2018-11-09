package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import exceptions.ImpossibleCreateAccountException;
import exceptions.ImpossibleCreateCardException;
import exceptions.ImpossibleDeactivateCardException;
import exceptions.ImpossibleModificateException;

public class Administracion {

	public Administracion() {}
	
	public void altaTarjeta(BigInteger nroTarjeta, BigInteger nroCuenta, BigDecimal limiteDesc, String tipoCuenta, Banco banco, 
			String nombre, String apellido, String cuit, String pwd) throws ImpossibleCreateCardException, IOException {
		TarjetaATM tarjeta;
		if (banco.cardIsOnRange(nroTarjeta)) {
			tarjeta = banco.cardIsOnWhitelist(nroTarjeta);
			if (tarjeta == null) {
				tarjeta = new TarjetaATM(nroTarjeta,pwd,true);
				Usuario usuario = new Usuario(nroTarjeta,apellido,nombre);
				Cuenta cuenta;
				if (tipoCuenta == "Cuenta Corriente") {
					cuenta = new CuentaCorriente(nroCuenta, BigDecimal.valueOf(300), BigDecimal.ZERO, BigDecimal.valueOf(10000), limiteDesc, "CUENTA CORRIENTE", 3);
				} else {
					if (tipoCuenta == "Caja de Ahorro") {
						cuenta = new CajaAhorro(nroCuenta, BigDecimal.valueOf(150), BigDecimal.ZERO, BigDecimal.valueOf(10000), limiteDesc, "CAJA AHORRO", 0);
					} else {
						cuenta = new CuentaSueldo(nroCuenta, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(10000), limiteDesc, "CUENTA SUELDO", 0, cuit);
					}
				}
				usuario.setCuenta(cuenta);
				banco.setCuentas(cuenta);
				banco.setTarjetas(tarjeta);
				tarjeta.setUsuario(usuario);
			} else {
				if (tarjeta.isHabilitada()) {
					throw new ImpossibleCreateCardException("La tarjeta ya existe y esta habilitada");
				} else {
					tarjeta.setHabilitada(true);
					tarjeta.setIntentosFallidos(0);
				}
				
			}
				
		} else {
			throw new ImpossibleCreateCardException("La tarjeta no esta en el rango del banco");
		}
				
	}
	
	public void altaCuenta(BigInteger nroTarjeta, BigInteger nroCuenta, BigDecimal limiteDesc, String tipoCuenta, Banco banco, String cuit) throws ImpossibleCreateAccountException {
		TarjetaATM tarjeta = banco.cardIsOnWhitelist(nroTarjeta);
		if (tarjeta != null) {
			Usuario usuario = tarjeta.getUsuario();
			Cuenta cuenta;
			if (tipoCuenta == "Cuenta Corriente") {
				cuenta = new CuentaCorriente(nroCuenta, BigDecimal.valueOf(300), BigDecimal.ZERO, BigDecimal.valueOf(10000), limiteDesc, "CUENTA CORRIENTE", 3);
			} else {
				if (tipoCuenta == "Caja de Ahorro") {
					cuenta = new CajaAhorro(nroCuenta, BigDecimal.valueOf(150), BigDecimal.ZERO, BigDecimal.valueOf(10000), limiteDesc, "CAJA AHORRO", 0);
				} else {
					cuenta = new CuentaSueldo(nroCuenta, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(10000), limiteDesc, "CUENTA SUELDO", 0, cuit);
				}
			}
			usuario.setCuenta(cuenta);
			banco.setCuentas(cuenta);
		} else {
			throw new ImpossibleCreateAccountException("No existe la tarjeta en el banco indicado");
		}
		
	}
	
	public void modificar(BigInteger nroTarjeta, BigInteger nroCuenta, BigDecimal limiteDesc, Banco banco, 
			String nombre, String apellido, String cuit, String pwd) throws ImpossibleModificateException {
		TarjetaATM tarjeta = banco.cardIsOnWhitelist(nroTarjeta);
		if (tarjeta != null) {
			Usuario usuario = tarjeta.getUsuario();
			Cuenta cuenta = usuario.buscarCuenta(nroCuenta);
			if (cuenta != null) {
				cuenta.setLimiteDescubierto(limiteDesc);
				if (cuenta.getTipoCuenta() == "CUENTA SUELDO") {
					((CuentaSueldo) cuenta).setCuitEmpresa(cuit);
				}
			}
			usuario.setApellido(apellido);
			usuario.setNombre(nombre);
			tarjeta.setPIN(pwd);
		} else {
			throw new ImpossibleModificateException("No existe la tarjeta en el banco indicado");
		}
		
	}
	
	public void baja(BigInteger nroTarjeta, Banco banco) throws ImpossibleDeactivateCardException {
		TarjetaATM tarjeta = banco.cardIsOnWhitelist(nroTarjeta);
		if (tarjeta != null) {
			tarjeta.setHabilitada(false);
		} else {
			throw new ImpossibleDeactivateCardException("No existe la tarjeta en el banco indicado");
		}
	}

}
