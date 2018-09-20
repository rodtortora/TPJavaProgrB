package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaCorriente extends Cuenta {

	public CuentaCorriente(BigInteger CBU, BigDecimal mantenimientoMensual, BigDecimal saldo, BigDecimal limiteExtraccionDiario, BigDecimal limiteDescubierto, String tipoCta, int limiteExtraccionesSC) {
		super(CBU, mantenimientoMensual, saldo,limiteExtraccionDiario,limiteDescubierto, tipoCta, limiteExtraccionesSC);
		// TODO Auto-generated constructor stub
	}

}
