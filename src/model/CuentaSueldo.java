package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaSueldo extends CajaAhorro {
	
	private String cuitEmpresa;

	public CuentaSueldo(BigInteger CBU, BigDecimal mantenimientoMensual, BigDecimal saldo,
			BigDecimal tasaExtraccOtroBanco, int limiteExtraccionesSC, BigDecimal tasaExtraccion,
			BigDecimal limiteExtraccionDiario, String cuitEmpresa, BigDecimal limiteDescubierto) {
		super(CBU, mantenimientoMensual, saldo, tasaExtraccOtroBanco, limiteExtraccionesSC, tasaExtraccion,
				limiteExtraccionDiario, limiteDescubierto);
		this.setCuitEmpresa(cuitEmpresa);

	}

	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

}
