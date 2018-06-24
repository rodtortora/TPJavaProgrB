package tp;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CajaAhorro extends Cuenta {
	
	private double tasaIntereses, limiteExtraccionDia;
	
	public CajaAhorro(BigInteger cBU, BigDecimal saldo, double tasaIntereses, double limiteExtraccionDia) {
		super(cBU, saldo);
		this.setTasaIntereses(tasaIntereses);
		this.setLimiteExtraccionDia(limiteExtraccionDia);
		// TODO Auto-generated constructor stub
	}

	public double getTasaIntereses() {
		return tasaIntereses;
	}

	public void setTasaIntereses(double tasaIntereses) {
		this.tasaIntereses = tasaIntereses;
	}

	public double getLimiteExtraccionDia() {
		return limiteExtraccionDia;
	}

	public void setLimiteExtraccionDia(double limiteExtraccionDia) {
		this.limiteExtraccionDia = limiteExtraccionDia;
	}
	@Override
	public String toString() {
		return "CajaAhorro";
	}


	@Override
	public BigDecimal getSaldoDisponible() {
		// TODO Auto-generated method stub
		return
	}
}
