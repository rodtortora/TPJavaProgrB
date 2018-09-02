package events;

import model.ATM;

public class PinSentEvent {
	
	private String pin;
	private ATM atm;
	
	public PinSentEvent(String pin) {
		this.setPin(pin);
	}
	
	public String getPin() {
		return this.pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setATM(ATM atm) {
		this.atm = atm;
	}

	public ATM getATM() {
		return this.atm;
	}

}
