package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaSueldo extends CajaAhorro {
	
	private String cuitEmpresa;

	public CuentaSueldo(BigInteger CBU, BigDecimal mantenimientoMensual, BigDecimal saldo, BigDecimal limiteExtraccionDiario, BigDecimal limiteDescubierto, String tipoCta, int limiteExtraccionesSC, String cuitEmpresa) {
		super(CBU, mantenimientoMensual, saldo, limiteExtraccionDiario, limiteDescubierto, tipoCta, limiteExtraccionesSC);
		this.setCuitEmpresa(cuitEmpresa);

	}

	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

}
