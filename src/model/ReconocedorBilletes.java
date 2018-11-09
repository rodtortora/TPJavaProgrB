package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

import exceptions.InvalidBillException;

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

	public boolean validar(BigInteger valorBillete, BigInteger cantidadBilletes) throws InvalidBillException {
		Iterator<BigInteger> itbilletesAceptados = billetesAceptados.iterator();
		while (itbilletesAceptados.hasNext()) {
			BigInteger billete = itbilletesAceptados.next();
			if (valorBillete.compareTo(billete) == 0) {
				return true;
			}
		}
		throw new InvalidBillException("Billete no reconocido");
	}

}
