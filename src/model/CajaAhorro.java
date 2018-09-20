package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CajaAhorro extends Cuenta {
	
	public CajaAhorro(BigInteger CBU, BigDecimal mantenimientoMensual, BigDecimal saldo, BigDecimal limiteExtraccionDiario, BigDecimal limiteDescubierto, String tipoCta, int limiteExtraccionesSC) {
		super(CBU, mantenimientoMensual, saldo, limiteExtraccionDiario, limiteDescubierto, tipoCta, limiteExtraccionesSC);
		
	}

}
