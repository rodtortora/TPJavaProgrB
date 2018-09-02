package events;

import model.ATM;

public class AtmSelectedEvent {
	
	private ATM atm;
	
	public AtmSelectedEvent(ATM atm) {
		this.setAtm(atm);
	}

	public ATM getAtm() {
		return atm;
	}

	public void setAtm(ATM atm) {
		this.atm = atm;
	}

}
