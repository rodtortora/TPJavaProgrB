package model;

import java.math.BigInteger;
import java.util.*;

public class Billetero {

	private HashMap<BigInteger, BigInteger> mapBilletes = new HashMap<BigInteger, BigInteger>();
	
	public void agregarBilletes(BigInteger valor, BigInteger cantidad) {
		mapBilletes.put(valor, cantidad.add(mapBilletes.get(cantidad)));
	}
	
	public void sacarBilletes(BigInteger valor, BigInteger cantidad) {
		if (mapBilletes.get(valor).subtract(cantidad).compareTo(BigInteger.ZERO) == -1) {
			//devolver error
		} else {
			mapBilletes.put(valor, cantidad.subtract(mapBilletes.get(valor)));
		}		
	}
	
	
	public BigInteger getSaldo() {
		BigInteger saldo = BigInteger.ZERO;
		mapBilletes.forEach((k,v) -> saldo.add(k.multiply(v)));
		return saldo;
	}

}
//3*100
//1*500
//1*1000
//que pasa si quiero extraer 1400??
