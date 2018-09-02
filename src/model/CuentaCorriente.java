package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaCorriente extends Cuenta {

	public CuentaCorriente(BigInteger CBU, BigDecimal mantenimientoMensual, BigDecimal saldo,
			BigDecimal tasaExtraccOtroBanco, int limiteExtraccionesSC, BigDecimal tasaExtraccion,
			BigDecimal limiteExtraccionDiario, BigDecimal limiteDescubierto, String tipoCuenta) {
		super(CBU, mantenimientoMensual, saldo, tasaExtraccOtroBanco, limiteExtraccionesSC, tasaExtraccion,
				limiteExtraccionDiario, limiteDescubierto, tipoCuenta);
		// TODO Auto-generated constructor stub
	}

}
