package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class ReconocedorBilletes {
	
	private ArrayList<BigInteger> billetesAceptados = new ArrayList<>();

	public ReconocedorBilletes(ArrayList<BigInteger> billetes) {
		this.setBilletesAceptados(billetes);
	}

	public ArrayList<BigInteger> getBilletesAceptados() {
		return billetesAceptados;
	}

	public void setBilletesAceptados(ArrayList<BigInteger> billetesAceptados) {
		this.billetesAceptados = billetesAceptados;
	}

	public boolean validar(BigInteger valorBillete, BigInteger cantidadBilletes) {
		Iterator<BigInteger> itbilletesAceptados = billetesAceptados.iterator();
		while (itbilletesAceptados.hasNext()) {
			BigInteger billete = itbilletesAceptados.next();
			if (valorBillete.compareTo(billete) == 0) {
				return true;
			}
		}
		return false;
	}

}
