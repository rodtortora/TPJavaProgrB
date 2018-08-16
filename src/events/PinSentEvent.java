package events;

public class PinSentEvent {
	
	private int pin;
	
	public PinSentEvent(int pin) {
		this.setPin(pin);
	}
	
	public int getPin() {
		return this.pin;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}

}
