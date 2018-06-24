package tp;

import java.math.BigInteger;

public class Billetero {

	private int valorBillete, cantidad;
	private BigInteger saldo;
	
	public Billetero(int valorBillete, int cantidad) {
		this.valorBillete = valorBillete;
		this.cantidad = cantidad;
	}
	public int getValorBillete() {
		return valorBillete;
	}
	public void setValorBillete(int valorBillete) {
		this.valorBillete = valorBillete;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigInteger getSaldo() {
		return saldo;
	}
	public void setSaldo(BigInteger saldo) {
		this.saldo = Math.multiplyExact(this.getCantidad(), this.getValorBillete());
	}

}
//3*100
//1*500
//1*1000
//que pasa si quiero extraer 1400??
