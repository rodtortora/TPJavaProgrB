package model;

import java.math.BigDecimal;
import java.util.Calendar;

public class Extraccion extends Transaction {

	public Extraccion(Calendar fechaTransaccion, BigDecimal moneyAmount) {
		super(fechaTransaccion, moneyAmount, TipoTransaccion.extraccion, true);
	}

}
