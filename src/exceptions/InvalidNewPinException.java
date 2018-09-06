package exceptions;

public class InvalidNewPinException extends Exception {
	
	public InvalidNewPinException() {}
	
	public InvalidNewPinException(String message) {
		super(message);
	}

}
