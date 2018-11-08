package model;

import java.math.BigDecimal;
import java.math.BigInteger;

import exceptions.CardNotFoundException;
import exceptions.ImpossibleCreateCardException;

public class Administracion {

	public Administracion() {}
	
	public void altaTarjeta(BigInteger nroTarjeta, BigInteger nroCuenta, BigDecimal limiteDesc, String tipoCuenta, Banco banco, 
			String nombre, String apellido, String cuit, String pwd) throws ImpossibleCreateCardException {
		if (banco.cardIsOnRange(nroTarjeta)) {
			if (banco.cardIsOnWhitelist(nroTarjeta) == null) {
				TarjetaATM tarjeta = new TarjetaATM(nroTarjeta,pwd,true);
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
			} else {
				throw new ImpossibleCreateCardException("La tarjeta ya existe");
			}
				
		} else {
			throw new ImpossibleCreateCardException("La tarjeta no esta en el rango del banco");
		}
				
	}

}
