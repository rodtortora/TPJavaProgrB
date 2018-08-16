package events;

public class PinSentEvent {
	
	private String pin;
	
	public PinSentEvent(String pin) {
		this.setPin(pin);
	}
	
	public String getPin() {
		return this.pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}

}
