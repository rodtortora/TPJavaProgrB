package events;

import model.Cuenta;

public class AccountSelectedEvent {
	
	private Cuenta cuenta;
	
	public AccountSelectedEvent(Cuenta cuenta) {
		this.setCuenta(cuenta);
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
