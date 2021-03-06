package model;

import java.math.BigInteger;

public class Billetero {

	private BigInteger valorBillete, cantidadBilletes, cantidadBilletesReservados;
	
	public Billetero(BigInteger valorBillete, BigInteger cantidad) {
		this.setCantidadBilletes(cantidad);
		this.setValorBillete(valorBillete);
	}

	public BigInteger getCantidadBilletes() {
		return cantidadBilletes;
	}

	public void setCantidadBilletes(BigInteger cantidad) {
		this.cantidadBilletes = cantidad;
	}
	
	public BigInteger getValorBillete() {
		return this.valorBillete;
	}
	
	public void setValorBillete(BigInteger valor) {
		this.valorBillete = valor;
	}

	public BigInteger getCantidadBilletesReservados() {
		return cantidadBilletesReservados;
	}

	public void setCantidadBilletesReservados(BigInteger cantidadBilletesReservados) {
		this.cantidadBilletesReservados = cantidadBilletesReservados;
	}

	public void expulsarDineroReservado() {
		this.setCantidadBilletes(this.getCantidadBilletes().subtract(this.getCantidadBilletesReservados()));	
	}
	
}
